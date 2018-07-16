package com.att.test.ub.filehandler;

import static org.junit.Assert.*;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;

import org.json.JSONArray;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.JUnit4;

import com.att.ub.filehandler.FileHandlerBO;

@RunWith(JUnit4.class)
public class FileHandlerBOHistoryTest {

    private FileHandlerBO fileHandlerBO = null;
    
    @Before
    public void setUp(){ 
	 fileHandlerBO = new FileHandlerBO();
    }
    
    @Test
    public void testGetFileDoesNotExists(){
	destroyFileUsedInTest();
	JSONArray fileHistory = fileHandlerBO.getFileSearchHistory(FileHandlerBO.DEFAULT_FILE_HISTORY);
	assertEquals(0, fileHistory.length());
    }
    
    @Test
    public void testGetFileExists(){
	createFileForTesting();
	JSONArray fileHistory = fileHandlerBO.getFileSearchHistory(FileHandlerBO.DEFAULT_FILE_HISTORY);
	assertNotNull(fileHistory);
	destroyFileUsedInTest();
    }
    
    @Test
    public void testGetFileListedAtLeastTwoInformation(){
	createFileForTesting();
	JSONArray fileHistory = fileHandlerBO.getFileSearchHistory(FileHandlerBO.DEFAULT_FILE_HISTORY);
	assertNotNull(fileHistory.get(0));
	assertNotNull(fileHistory.get(1));
	destroyFileUsedInTest();
    }

    @Test
    public void testGetFileListedOneInformation(){
	createFileForTesting();
	int quantity = 1;
	JSONArray fileHistory = fileHandlerBO.getFileSearchHistory(quantity);
	assertNotNull(fileHistory.get(0));
	assertEquals(quantity, fileHistory.length());
	destroyFileUsedInTest();
    }
    
    @Test
    public void testIfFileCanBeWriten(){
	boolean fileCanBeWriten = fileHandlerBO.putHistoryRecord("/path/to/file/folder/", "file1.dat");
	assertTrue(fileCanBeWriten);
    }
    
    // TODO : more tests regarding the file writer is needed
    
    private void destroyFileUsedInTest() {
	File toRemove = new File("history.json");
	if (toRemove.exists()){
	    toRemove.delete();
	}
	
    }

    private void createFileForTesting(){
	PrintWriter writer = null;
	try {
	    writer = new PrintWriter("history.json", "UTF-8");
	    writer.println("[{");
	    writer.println("value:\"arquivo1.dat\",");
	    writer.println("source:\"/var/svn/teste/\",");
	    writer.println("},");
	    writer.println("{");
	    writer.println("value:\"arquivo2.dat\",");
	    writer.println("source:\"/var/svn/teste/\",");
	    writer.println("}]");
	} catch (FileNotFoundException e) {
	    e.printStackTrace();
	} catch (UnsupportedEncodingException e) {
	    e.printStackTrace();
	} finally {
	    if (writer != null){
		writer.close();
	    }
	}
    }
    
}
