package com.att.test.ub.auth;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.URL;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

import com.att.ub.Startup;
import com.att.ub.auth.htpasswd.HtPasswdUserDetailsService;

public class HtPasswdUserDetailsServiceTest {

	private HtPasswdUserDetailsService service = null;

	@Before
	public void setUp() {
		service = new HtPasswdUserDetailsService();
	}

	@Test(expected = UsernameNotFoundException.class)
	public void testNoUserFound() {
		String userName = "unknown-user";
		service.loadUserByUsername(userName);
		Assert.assertTrue(false);
	}

	@Test
	public void testDefaultUserFound() {
		String userName = "admin";
		UserDetails loadUserByUsername = service.loadUserByUsername(userName);
		Assert.assertNotNull(loadUserByUsername);
	}

	// @Test
	public void testUserFoundFile() {
		createFileForTesting();
		String userName = "llicursi";
		UserDetails loadUserByUsername = service.loadUserByUsername(userName);
		Assert.assertNotNull(loadUserByUsername);
		destroyFileUsedInTest();
	}

	private void createFileForTesting() {
		PrintWriter writer = null;
		URL resource = Startup.class.getResource("/");
		try {
			writer = new PrintWriter(resource.getPath() + File.separatorChar + ".htpasswd", "UTF-8");
			writer.println("llicursi:$apr1$vWMumGzZ$iRUeZGbWX0N10EeBirP.j/");
			writer.println("other:$apr1$vWMumGzZ$iRUeZGbWX0N10EeBirP.j/");
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		} finally {
			if (writer != null) {
				writer.close();
			}
		}
	}

	private void destroyFileUsedInTest() {
		URL resource = Startup.class.getResource("/");
		File toRemove = new File(resource.getPath() + File.separatorChar + ".htpasswd");
		if (toRemove.exists()) {
			toRemove.delete();
		}
	}

}
