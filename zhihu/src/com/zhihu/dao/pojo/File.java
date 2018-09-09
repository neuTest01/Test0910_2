package com.zhihu.dao.pojo;

/**
 * Created by Administrator on 2017-04-19.
 */
public class File {

    private Integer fileId;

    private String fileName;

    private String fileRealName;

    private String fileUrl;

    private String fileType;

    private String fileCreateTime;

    private oracle.sql.ROWID rowid  ;	
 
    public oracle.sql.ROWID getRowid() {
		return rowid;
	}

	public void setRowid(oracle.sql.ROWID rowid) {
		this.rowid = rowid;
	}

	public Integer getFileId() {
        return fileId;
    }

    public void setFileId(Integer fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileRealName() {
        return fileRealName;
    }

    public void setFileRealName(String fileRealName) {
        this.fileRealName = fileRealName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileType() {
        return fileType;
    }

    public void setFileType(String fileType) {
        this.fileType = fileType;
    }

    public String getFileCreateTime() {
        return fileCreateTime;
    }

    public void setFileCreateTime(String fileCreateTime) {
        this.fileCreateTime = fileCreateTime;
    }
}
