package com.demo.novel.util;

public class Linker {
    private String fileUrl;

    public Linker(String fileUrl, String fileName) {
        this.fileUrl = fileUrl;
        this.fileName = fileName;
    }

    private String fileName;

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }
}
