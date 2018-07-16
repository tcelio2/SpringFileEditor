package com.att.ub.auth;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class AuthenticationController {

    private static final String URL_PRIOR_LOGIN = "url_prior_login";
    private final Logger LOGGER = Logger.getLogger(AuthenticationController.class);

    @RequestMapping(value = { "/login", "/login/" }, method = RequestMethod.GET)
    public String login(Model model, HttpServletRequest request, @RequestParam(required = false) String error) {

	String referrer = request.getHeader("Referer");
	if (referrer != null) {
	    request.getSession().setAttribute(URL_PRIOR_LOGIN, referrer);
	}

	if (error != null && !error.isEmpty()) {
	    LOGGER.error("Invalid username or password");
	    model.addAttribute("error-message", "Invalid username or password");
	}
	model.addAttribute("nopass", true);

	return "login";
    }

   
}
