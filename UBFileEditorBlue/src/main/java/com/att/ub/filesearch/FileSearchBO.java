package com.att.ub.filesearch;

import java.util.List;

import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.att.ub.exceptions.FileSearchNotFoundException;

@Component
public class FileSearchBO {

	@Autowired
	private FileSearchDAO fileSearchDAO = null;
	private final static String JSON_PROPERTY_NAME = "name";

	public String listFilePath(String path) throws FileSearchNotFoundException {

		List<String> files = fileSearchDAO.getFiles(path);

		if (files == null) {
			throw new FileSearchNotFoundException();
		}

		JSONArray fsList = new JSONArray();
		for (int i = 0; i < files.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			String fileName = files.get(i);
			jsonObject.put(JSON_PROPERTY_NAME, fileName);
			fsList.put(jsonObject);
		}

		return fsList.toString();
	}

	public List<String> getCopybookBO(String copybook) {

		return fileSearchDAO.extractCopybook(copybook);
	}

	public String listDirectories(boolean isFilePath, boolean isTargetCopybook) {

		List<String> files = fileSearchDAO.listDirectories(isFilePath,isTargetCopybook);
		JSONArray fsList = new JSONArray();
		for (int i = 0; i < files.size(); i++) {
			JSONObject jsonObject = new JSONObject();
			String fileName = files.get(i);
			jsonObject.put(JSON_PROPERTY_NAME, fileName);
			fsList.put(jsonObject);
		}

		return fsList.toString();
	}
}
