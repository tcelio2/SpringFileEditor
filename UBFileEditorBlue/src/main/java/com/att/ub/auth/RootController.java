package com.att.ub.auth;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.session.SessionRegistry;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class RootController {
    
    private static final String PATH_FILE_NAME = "pathfile";
    private static final String FILE_NAME = "file";
    
    @Autowired
    private SessionRegistry sessionRegistry; 
    
    @Value("${ub.path.filehandler}")
    private String filehandler;
    
    @RequestMapping("/")
    public String index(Model model, HttpServletRequest req){
	
	Object oFileName = req.getSession().getAttribute("filename");
	String fileName = (oFileName != null) ? oFileName.toString() : "";
	
	model.addAttribute(PATH_FILE_NAME, filehandler);
	model.addAttribute(FILE_NAME, fileName);
	
	return "main";
    }
    
    @RequestMapping("/listSessions")
    @ResponseBody
    public String listSessions(HttpServletRequest request){
	
	String response = "";
	List<Object> allPrincipals = sessionRegistry.getAllPrincipals();
	for (Object user : allPrincipals){
	    if (user instanceof UserDetails) {
		UserDetails new_name = (UserDetails) user;
		response += " " + new_name.getUsername();
	    }
	    //List<SessionInformation> allSessions = sessionRegistry.getAllSessions(user, true);
	}
	
	
	return response;
    }
    
    
    
}
