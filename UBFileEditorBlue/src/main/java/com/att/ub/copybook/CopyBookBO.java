package com.att.ub.copybook;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.att.ub.exceptions.CopyBookNotFoundException;

@Service
public class CopyBookBO {

	@Autowired
	private USCopybookDAO copybookDAO;

	@Value("${ub.path.copybook}")
	private String copybookPath;

	public String copyBookList() throws CopyBookNotFoundException {
		return copybookDAO.CopyBookList(copybookPath);
	}

	public CopybookDTO copybookParser(String xmlFile) {
		return copybookDAO.extractCopybook(xmlFile, copybookPath);
	}

}
