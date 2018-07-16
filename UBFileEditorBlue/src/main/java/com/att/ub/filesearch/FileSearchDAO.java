package com.att.ub.filesearch;

import java.io.File;
import java.util.ArrayList;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import com.att.ub.exceptions.FileSearchNotFoundException;

@Service
public class FileSearchDAO {

	@Value("${ub.path.filehandler}")
	private String filePath;

	@Value("${ub.path.copybookhandler}")
	private String copybookpath;

	@Value("${ub.path.copybooktarget}")
	private String copybooktarget;

	public List<String> getFiles(String path) {
		File dir = new File(path);
		File[] file = dir.listFiles();
		List<String> list = new ArrayList<String>();

		if (file != null) {

			int length = file.length;

			for (int i = 0; i < length; ++i) {

				if (file[i].isFile()) {
					list.add(file[i].getName());
				}

			}

		}
		// Reading directory contents		
		return list;
	}

	public List<String> extractCopybook(String copybook) {
		Document document = null;

		try {
			// Get Document Builder
			DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
			DocumentBuilder builder = factory.newDocumentBuilder();

			File file = new File(copybook);

			// Build Document
			document = builder.parse(file);

			// Normalize the XML Structure; It's just too important !!
			document.getDocumentElement().normalize();

			// Here comes the root node
			Element root = document.getDocumentElement();

			// Get all employees
			NodeList nList = root.getChildNodes();

			for (int temp = 0; temp < nList.getLength(); temp++) {
				Node node = nList.item(temp);

				if (node.getNodeType() == Node.ELEMENT_NODE && node.getNodeName().equals("field-list") && node.hasChildNodes()) {
					return fieldListChildren(node.getChildNodes());
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return null;
	}

	public List<String> fieldListChildren(NodeList nList) {
		for (int temp = 0; temp < nList.getLength(); temp++) {
			Node node = nList.item(temp);

			if (node.getNodeType() == Node.ELEMENT_NODE && (node.getNodeName().equals("field") || node.getNodeName().equals("c88-variable"))) {

				List<String> originalNameList = new ArrayList<String>();

				NodeList nFieldList = node.getChildNodes();

				for (int temp1 = 0; temp1 < nFieldList.getLength(); temp1++) {
					Node nodeField = nFieldList.item(temp1);
					if (nodeField.getNodeType() == Node.ELEMENT_NODE) {

						if (nodeField.getNodeName().equals("original-name")) {
							originalNameList.add(nodeField.getTextContent());
						}
					}
				}
				return originalNameList;
			}
		}
		return null;
	}

	public List<String> readFilesFromPath(String folderPath) throws FileSearchNotFoundException {

		File folder = new File(folderPath);

		if (folder == null || !folder.isDirectory()) {
			throw new FileSearchNotFoundException();
		}

		File[] listOfFiles = folder.listFiles();
		List<String> files = new ArrayList<String>();

		for (int i = 0; i <= listOfFiles.length - 1; i++) {
			if (listOfFiles[i].isFile()) {
				String fileName = listOfFiles[i].getName();
				files.add(fileName);
			}
		}
		return files;

	}

	public List<String> listDirectories(boolean isFilePath, boolean isTargetCopybook) {

		String path;
		if (isFilePath) {
			path = filePath;
		} else if (isTargetCopybook) {
			path = copybooktarget;
		} else {
			path = copybookpath;
		}

		File folder = new File(path);
		File[] files = folder.listFiles();
		List<String> outputFiles = new ArrayList<String>();
		for (int i = 0; i < files.length; i++) {
			if (files[i].isDirectory()) {
				System.out.println("dir" + files[i].getName());
				outputFiles.add(path + File.separatorChar + files[i].getName());
			}
		}
		return outputFiles;

	}
}
