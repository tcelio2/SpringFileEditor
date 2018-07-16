package com.att.ub.filehandler;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class FileHandlerController {
    
    @Value("${ub.path.filehandler}")
    private String filehandler;

    @RequestMapping(value = { "/filehandler/", "/filehandler" }, method=RequestMethod.POST)
    @ResponseBody
    public byte[] lastused(@RequestParam Integer navNumber, HttpServletRequest req) {
	
	Object oFileName = req.getSession().getAttribute("filename");
	String fileName = (oFileName != null) ? filehandler + oFileName.toString() : "";
	
	FileHandlerBO fileHandlerBO = new FileHandlerBO();
	
	return fileHandlerBO.getSnippetCode(fileName, navNumber);
    }
    
}
