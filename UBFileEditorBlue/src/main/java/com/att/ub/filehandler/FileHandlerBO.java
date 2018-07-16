package com.att.ub.filehandler;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class FileHandlerBO {

    private static final Logger LOGGER = Logger.getLogger(FileHandlerBO.class);
    public static final int DEFAULT_FILE_HISTORY = 10;

    private FileHandlerDAO fileHandlerDao = null;

    /**
     * List a quantity of records from the history file.
     * 
     * @param quantity
     * @return Records from history
     */
    public JSONArray getFileSearchHistory(int quantity) {
	LOGGER.debug("getFileHistory (" + quantity + ")");
	JSONArray listHistory = getFileHandlerDao().getFileHistory();
	if (listHistory == null) {
	    LOGGER.debug("Nothing found");
	    return new JSONArray();
	}
	JSONArray filtredListHistory = new JSONArray();

	for (int i = 0; i < listHistory.length() && i < quantity; i++) {
	    Object recordFromHistory = listHistory.get(i);
	    filtredListHistory.put(recordFromHistory);
	}

	return filtredListHistory;
    }

    public byte[] getSnippetCode(String file, Integer navNumber) {
	
	return getFileHandlerDao().getRecordByChunk(file, navNumber);

    }

    public boolean putHistoryRecord(String path, String file) {
	JSONObject newHistoryRecord = new JSONObject();
	newHistoryRecord.put("path", path);
	newHistoryRecord.put("file", file);
	return getFileHandlerDao().putHistoryRecord(newHistoryRecord);
    }

    private FileHandlerDAO getFileHandlerDao() {
	if (fileHandlerDao == null) {
	    fileHandlerDao = new FileHandlerDAO();
	}
	return fileHandlerDao;
    }

}
