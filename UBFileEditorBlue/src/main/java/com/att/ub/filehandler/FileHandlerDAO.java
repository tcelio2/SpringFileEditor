package com.att.ub.filehandler;

import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.InputStream;

import org.apache.log4j.Logger;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.stereotype.Service;

@Service
public class FileHandlerDAO {

    private static final String HISTORY_FILE_NAME = "history.json";
    private static final Logger LOGGER = Logger.getLogger(FileHandlerDAO.class);

    public JSONArray getFileHistory() {
	LOGGER.debug("getFileHistory() - " + HISTORY_FILE_NAME);
	File jsonHistoryFile = new File(HISTORY_FILE_NAME);
	if (jsonHistoryFile.exists()) {
	    FileInputStream fileInputStream = null;
	    try {
		fileInputStream = new FileInputStream(jsonHistoryFile);
		JSONTokener fileParser = new JSONTokener(fileInputStream);
		return (JSONArray) fileParser.nextValue();
	    } catch (JSONException e) {
		LOGGER.error(e);
	    } catch (FileNotFoundException e) {
		LOGGER.error(e);
	    } finally {
		try {
		    if (fileInputStream != null) {
			fileInputStream.close();
		    }
		} catch (IOException e) {
		    LOGGER.error("File closure failure", e);
		}
	    }

	}
	LOGGER.debug("File not found");
	return null;
    }

    //TODO: Change the variables name;
    //Modify the logic and the methods to not read the same file twice;
    //The logic is working only with the first navigation page, modify the code to read the next chunks.
    //The bufferSize is hardcode. Change to read a chunk of 300KB in each navigation page.
    
    public byte[] getRecordByChunk(String filePath, Integer navNumber) {

	File file = null;
	InputStream is = null;
	BufferedInputStream bis = null;
	Integer bufferSize = 1024 * 2; //2KB
	byte[] bytes = new byte[bufferSize];

	try {

	    // Receive the input file path
	    file = new File(filePath);
	    is = new FileInputStream(file);
	    bis = new BufferedInputStream(is);

	    int c;
	    int count = 0;
	    int byteFinalLine = 0;
	        
	    while( (c = bis.read()) > 1 ){
		    
		String x = Character.toString((char)c);
		   
		if(x.equals("\n") && count < bufferSize){
		       
		    byteFinalLine = count;
		       
		}
		    
		count++;
	    }
		
	    bytes = getRecordByFinalLine(filePath, navNumber, byteFinalLine);
	    
	    return bytes;

	} catch (IOException e) {

	    LOGGER.error("The system cannot find the file specified.", e);

	    return bytes;
	} catch (Exception e) {

	    LOGGER.error("The system cannot find the file specified.", e);

	    return bytes;

	} finally {

	    try {

		// Closing files
		if (bis != null) {
		    bis.close();
		}

	    } catch (IOException e) {

		LOGGER.error("File closure failure", e);

	    }

	}

    }
    
    
    
    
    
    public byte[] getRecordByFinalLine(String filePath, Integer navNumber, Integer byteFinalLine) {

	File file = null;
	InputStream is = null;
	BufferedInputStream bis = null;
	Integer bufferSize = 1024 * 2; //2KB
	byte[] bytes = new byte[bufferSize];

	try {

	    // Receive the input file path
	    file = new File(filePath);
	    is = new FileInputStream(file);
	    bis = new BufferedInputStream(is);

		 	
	    bis.skip(navNumber);
            bis.read(bytes, 0, byteFinalLine);
	    
	    return bytes;

	} catch (IOException e) {

	    LOGGER.error("The system cannot find the file specified.", e);

	    return bytes;
	} catch (Exception e) {

	    LOGGER.error("The system cannot find the file specified.", e);

	    return bytes;

	} finally {

	    try {

		// Closing files
		if (bis != null) {
		    bis.close();
		}

	    } catch (IOException e) {

		LOGGER.error("File closure failure", e);

	    }

	}

    }

    /**
     * Includes a new record into the history.json at the top of the file. If the file does not exist, create it.
     * 
     * @param newHistoryRecord
     */
    public boolean putHistoryRecord(JSONObject newHistoryRecord) {
	LOGGER.debug("putHistoryRecord" + newHistoryRecord.toString());
	JSONArray existingHistoryList = getFileHistory();

	if (existingHistoryList == null) {
	    existingHistoryList = new JSONArray();
	}

	JSONArray prependedHistoryList = new JSONArray();
	prependedHistoryList.put(newHistoryRecord);
	for (int i = 0; i < existingHistoryList.length(); i++) {
	    prependedHistoryList.put(existingHistoryList.get(i));
	}

	FileWriter historyWriterToFile = null;
	try {
	    historyWriterToFile = new FileWriter(HISTORY_FILE_NAME);
	    prependedHistoryList.write(historyWriterToFile);
	    historyWriterToFile.close();
	    historyWriterToFile = null;
	    LOGGER.debug("History written successifully");
	    return true;
	} catch (IOException e) {
	    LOGGER.error(e);
	} finally {
	    if (historyWriterToFile != null) {
		try {
		    historyWriterToFile.close();
		} catch (IOException e) {
		    LOGGER.error("Failure while closing the file: " + HISTORY_FILE_NAME, e);
		}
	    }
	}

	return false;

    }

}
