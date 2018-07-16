package com.att.test.ub.auth;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.att.ub.auth.htpasswd.HtpasswdPasswordEncoder;

public class HtPassedPasswordEncoderTest {

	private HtpasswdPasswordEncoder passEncoder = null;

	@Before
	public void setUp() {
		passEncoder = new HtpasswdPasswordEncoder(); 
	}

	@Test
	public void testIfFoundTheOSName() {
		Assert.assertTrue(passEncoder.isPlainTextPwdSupported());
	}

	@Test
	public void testMd5PassedIsValid() {
		CharSequence rawPassword = "123";
		String encodedPassword = "$apr1$vWMumGzZ$iRUeZGbWX0N10EeBirP.j/";
		Assert.assertTrue(passEncoder.matches(rawPassword, encodedPassword));
	}

	@Test
	public void testMd5PassedIsInvalid() {
		CharSequence rawPassword = "1234";
		String encodedPassword = "$apr1$vWMumGzZ$iRUeZGbWX0N10EeBirP.j/";
		Assert.assertTrue(!passEncoder.matches(rawPassword, encodedPassword));
	}

}
