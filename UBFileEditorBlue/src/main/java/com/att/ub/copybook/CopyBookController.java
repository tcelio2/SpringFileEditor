package com.att.ub.copybook;

import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.att.ub.exceptions.CopyBookNotFoundException;

@RequestMapping("/copybook")
@RestController
public class CopyBookController {

    @Autowired
    private CopyBookBO copybookBO;

    @Value("${ub.path.copybook}")
    private String copybookPath;

    @RequestMapping(value = { "/", "" })
    public String copyBookList() {
	try {
	    return copybookBO.copyBookList();
	} catch (CopyBookNotFoundException e) {
	    return (new JSONObject()).toString();
	}
    }

    @RequestMapping(value = { "/{xmlFile}/" })
    public CopybookDTO copyBook(@PathVariable("xmlFile") String xmlFile) {
	return copybookBO.copybookParser(xmlFile);
    }

}
