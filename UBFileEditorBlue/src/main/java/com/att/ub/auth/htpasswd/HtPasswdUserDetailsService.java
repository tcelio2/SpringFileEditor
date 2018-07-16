package com.att.ub.auth.htpasswd;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.att.ub.Startup;

@Service
public class HtPasswdUserDetailsService implements UserDetailsService {
    
    private final Logger LOGGER = Logger.getLogger(HtPasswdUserDetailsService.class);
    
    private static final String SPLITTER_HTPASSWD_CHAR = ":";

    @Value("${ub.file.htpasswd}")
    private String htpassedFileName ;
    
    @Value("${ub.file.htpasswd.default.user}")
    private String htpassedDefaultUsername = "admin";
    
    @Value("ub.file.htpasswd.default.pass")
    private String htpassedDefaultUserPass = "$apr1$vWMumGzZ$iRUeZGbWX0N10EeBirP.j/";
    
    private Map<String, String> currentListOfUsers = null;
    
    
    @Override
    public UserDetails loadUserByUsername(String userName) throws UsernameNotFoundException {
	UserDetails user = recoverUserFromFile(userName);
	if (user == null){
	    throw new UsernameNotFoundException(String.format("User %s not found", userName));
	}
	return user;
    }
    
    private Map<String, String> getCurrentListOfUsers(){
	if (currentListOfUsers == null){
	   this.currentListOfUsers = readMapOfUsersFromFile();
	}
	return this.currentListOfUsers;
    }
    
    private String getRootPath(){
	URL resource = Startup.class.getResource("/");
	LOGGER.debug("Searching " + htpassedFileName + " at : " + resource);
	return resource.getPath();
    }
    
    private Map<String, String> readMapOfUsersFromFile() {
	Map<String, String> mapOfUsers = new HashMap<String, String>();
	File fileHtpasswd = new File(getRootPath() + File.separatorChar + htpassedFileName);
	if (fileHtpasswd.exists()){
	    InputStream fis = null;
	    BufferedReader br = null;
	    try {
		fis = new FileInputStream(fileHtpasswd);
		InputStreamReader isr = new InputStreamReader(fis, Charset.forName("UTF-8"));
		br = new BufferedReader(isr);
		String line;
		while ((line = br.readLine()) != null){
		    if (!line.trim().isEmpty() && line.contains(SPLITTER_HTPASSWD_CHAR)){
			int indexOfSeparatorChar = line.indexOf(SPLITTER_HTPASSWD_CHAR);
			String name = line.substring(0, indexOfSeparatorChar);
			String pass = line.substring(indexOfSeparatorChar +1 );
			mapOfUsers.put(name, pass);
		    }
		}
		LOGGER.debug("Users read from htpasswd file :" + mapOfUsers.keySet().size());
	    } catch (FileNotFoundException e) {
		LOGGER.error(e);
	    } catch (IOException e) {
		LOGGER.error(e);
	    } finally {
		if (br != null){
		    try {
			br.close();
		    } catch (IOException e) {
			LOGGER.error(e);
		    }
		}
	    }
	  
	}
	
	if (mapOfUsers.keySet().size() == 0){
	    LOGGER.info("Using default configuration - " + htpassedDefaultUsername +":"+ htpassedDefaultUserPass); 
	    mapOfUsers.put(htpassedDefaultUsername, htpassedDefaultUserPass);
	}
	return mapOfUsers;
    }
  
    private HtPasswdUserDetails recoverUserFromFile(String name){
	if (!getCurrentListOfUsers().containsKey(name)){
	    return null;
	}
	String passd = getCurrentListOfUsers().get(name);
	return new HtPasswdUserDetails(name, passd);
	
    }
    
}
