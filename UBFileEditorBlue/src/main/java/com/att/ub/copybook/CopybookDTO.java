package com.att.ub.copybook;

import java.util.ArrayList;
import java.util.List;

public class CopybookDTO {
	
	private String originalName;
	private long size;
	private String fileName;
	List<CopybookField> fieldList;
	
	public CopybookDTO(){
		fieldList = new ArrayList<CopybookField>(); 
	}
	
	public void addCopybookField(CopybookField cbField){
		fieldList.add(cbField);
	}
	
	public String getOriginalName() {
		return originalName;
	}
	
	public void setOriginalName(String originalName) {
		this.originalName = originalName;
	}
	
	public long getSize() {
		return size;
	}
	
	public void setSize(long size) {
		this.size = size;
	}
	
	public String getFileName() {
		return fileName;
	}
	
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	
	public List<CopybookField> getFieldList() {
		return fieldList;
	}
	
	public void setFieldList(List<CopybookField> fieldList) {
		this.fieldList = fieldList;
	}
	
}
