package com.ari.project.domain;

public class UploadedFile {
    private String filename = "none";
    private String type = "none";
    private String ext = ".nothing";
    private String content = "";
    private boolean uploaded = false;

    public UploadedFile(){
    }
    public UploadedFile(UploadedFile uploadedFile){
        filename = uploadedFile.getFilename();
        type = uploadedFile.getType();
        ext = uploadedFile.getExt();
        content = uploadedFile.getContent();
        uploaded = uploadedFile.isUploaded();
    }

    public String getFilename() {
        return filename;
    }

    public void setFilename(String filename) {
        this.filename = filename;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public boolean isUploaded() {
        return uploaded;
    }

    public void setUploaded(boolean uploaded) {
        this.uploaded = uploaded;
    }

    public String getExt() {
        return ext;
    }

    public void setExt(String ext) {
        this.ext = ext;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
