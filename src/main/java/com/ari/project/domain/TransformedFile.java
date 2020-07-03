package com.ari.project.domain;

import java.util.ArrayList;
import java.util.List;

public class TransformedFile {
    private String type = "";
    private String document = "";

    public TransformedFile(){};

    public TransformedFile(String type, String document){
        this.type = type;
        this.document = document;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }
}
