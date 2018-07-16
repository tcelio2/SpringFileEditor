package com.att.ub.copybook;

import java.io.File;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Repository;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.att.ub.exceptions.CopyBookNotFoundException;

@Repository
public class USCopybookDAO implements CopyBookDAO {

	private CopybookDTO cbDTO;

	@Override
	public String CopyBookList(String copybookPath) throws CopyBookNotFoundException{
		File file = new File(copybookPath);

		File[] files = file.listFiles();

		if (files == null) {
			throw new CopyBookNotFoundException();
		}

		JSONArray cbList = new JSONArray();
		for (int i = 0; i < files.length; i++) {
			String fileName = files[i].getName();
			if (fileName.substring(fileName.length() - 4, fileName.length())
					.equals(".xml")) {
				JSONObject jsonObject = new JSONObject();

				jsonObject.put("Name",
						fileName.substring(0, fileName.length() - 4));
				cbList.put(jsonObject);
			}
		}

		return cbList.toString();
	}

	@Override
	public Document docParser(String xmlFile, String copybookPath) {
		Document document = null;
		try {
			// Get Document Builder
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			File file = new File(copybookPath + xmlFile + ".xml");

			// Build Document
			document = builder.parse(file);

			// Normalize the XML Structure; It's just too important !!
			document.getDocumentElement().normalize();

		} catch (Exception e) {
			e.printStackTrace();
		}
		return document;
	}

	@Override
	public CopybookDTO extractCopybook(String xmlFile, String copybookPath) {
		Document document = null;
		cbDTO = null;
		try {
			// Get Document Builder
			DocumentBuilderFactory factory = DocumentBuilderFactory
					.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			File file = new File(copybookPath + xmlFile + ".xml");

			// Build Document
			document = builder.parse(file);

			// Normalize the XML Structure; It's just too important !!
			document.getDocumentElement().normalize();

			// Here comes the root node
			Element root = document.getDocumentElement();

			// Get all employees
			NodeList nList = root.getChildNodes();
			cbDTO = new CopybookDTO();
			visitChildNodes(nList);

		} catch (Exception e) {
			e.printStackTrace();
		}
		return cbDTO;
	}

	@Override
	public void visitChildNodes(NodeList nList) {

		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE) {
				switch (node.getNodeName()) {
				case "original-name":
					cbDTO.setOriginalName(node.getTextContent());
					break;

				case "sm-record-size":
					cbDTO.setSize(Long.valueOf(node.getTextContent()));
					break;

				case "assigned-file-names":
					cbDTO.setFileName(node.getChildNodes().item(1).getTextContent());
					break;

				case "field-list":
					if (node.hasChildNodes()) {
						fieldListChildren(node.getChildNodes());
					}
					break;

				default:
					break;
				}
			}
		}
	}

	@Override
	public void fieldListChildren(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE
					&& (node.getNodeName().equals("field") || node
							.getNodeName().equals("c88-variable"))) {

				CopybookField cField = new CopybookField();
				Element eParent = (Element)node;
				cField.setName(eParent.getAttribute("name"));
				cField.setFieldType(eParent.getAttribute("field-type"));
				cField.setMode(eParent.getAttribute("mode"));
				
				NodeList nFieldList = node.getChildNodes();
				
				for (int temp1 = 0; temp1 < nFieldList.getLength(); temp1++) {
					Node nodeField = nFieldList.item(temp1);
					if (nodeField.getNodeType() == Node.ELEMENT_NODE) {
						Element eChildren = (Element)nodeField;
						
						if(nodeField.getNodeName().equals("original-name")){
							cField.setOriginalName(nodeField.getTextContent());
						}
						else if(nodeField.getNodeName().equals("field-size")){
							cField.setDisplay(Integer.valueOf(eChildren.getAttribute("display")));
							cField.setDisplay(Integer.valueOf(eChildren.getAttribute("storage")));
						}
					}
				}
				cbDTO.addCopybookField(cField);
			}
		}
	}
}
