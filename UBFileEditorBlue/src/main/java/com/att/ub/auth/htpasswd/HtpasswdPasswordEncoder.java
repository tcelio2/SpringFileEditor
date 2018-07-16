package com.att.ub.auth.htpasswd;

import org.apache.commons.codec.binary.Base64;
import org.apache.commons.codec.digest.Crypt;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.commons.codec.digest.Md5Crypt;
import org.apache.log4j.Logger;
import org.springframework.security.crypto.password.PasswordEncoder;

public class HtpasswdPasswordEncoder implements PasswordEncoder {

	private final Logger LOGGER = Logger.getLogger(HtpasswdPasswordEncoder.class);
	private boolean supportPlainTextPwd = false;

	public HtpasswdPasswordEncoder() {
		checkOS();
	}

	private void checkOS() {
		String os = System.getProperty("os.name").toLowerCase();
		if (os.startsWith("windows") || os.startsWith("netware")) {
			supportPlainTextPwd = true;
		}
	}

	@Override
	public String encode(CharSequence rawPassword) {
		// do nothing   
		return null;
	}

	@Override
	public boolean matches(CharSequence rawPassword, String encodedPassword) {
		if (encodedPassword != null) {
			final String passwd = rawPassword.toString();

			// test Apache MD5 variant encrypted password
			if (encodedPassword.startsWith("$apr1$")) {
				if (encodedPassword.equals(Md5Crypt.apr1Crypt(passwd, encodedPassword))) {
					LOGGER.debug("Apache MD5 encoded password matched for  '" + encodedPassword + "'");
					return true;
				}

			}
			// test unsalted SHA password
			else if (encodedPassword.startsWith("{SHA}")) {
				String passwd64 = Base64.encodeBase64String(DigestUtils.sha1(passwd));
				if (encodedPassword.substring("{SHA}".length()).equals(passwd64)) {
					LOGGER.debug("Unsalted SHA-1 encoded password matched for  '" + encodedPassword + "'");
					return true;
				}
			}

			// test libc crypt() encoded password
			else if (!supportPlainTextPwd && encodedPassword.equals(Crypt.crypt(passwd, encodedPassword))) {
				LOGGER.debug("Libc crypt encoded password matched for  '" + encodedPassword + "'");
				return true;
			}

			// test clear text
			else if (supportPlainTextPwd && encodedPassword.equals(passwd)) {
				LOGGER.debug("Clear text password matched for user '" + encodedPassword + "'");
				return true;
			}
		}

		return false;
	}

	public boolean isPlainTextPwdSupported() {
		return this.supportPlainTextPwd;
	}

}
