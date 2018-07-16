package com.att.ub.copybook;

import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;

import com.att.ub.exceptions.CopyBookNotFoundException;

@Service
public interface CopyBookDAO {
   
	public String CopyBookList(String copybookPath) throws CopyBookNotFoundException;
	public Document docParser(String xmlFile, String copybookPath);
	public CopybookDTO extractCopybook(String xmlFile, String copybookPath);
	public void visitChildNodes(NodeList nList);
	public void fieldListChildren(NodeList nList);

}
