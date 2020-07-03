package com.ari.project.domain;

import java.util.ArrayList;
import java.util.List;

public class UploadedFile {
    private String filename = "none";
    private String type = "none";
    private String ext = ".nothing";
    private String content = "";
    private boolean created = false;

    public UploadedFile(){
    }
    public UploadedFile(UploadedFile uploadedFile){
        filename = uploadedFile.getFilename();
        type = uploadedFile.getType();
        ext = uploadedFile.getExt();
        content = uploadedFile.getContent();
        created = uploadedFile.isCreated();
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

    public boolean isCreated() {
        return created;
    }

    public void setCreated(boolean created) {
        this.created = created;
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
