package com.ari.project.form;

import com.ari.project.domain.UploadedFile;

public class UploadForm extends UploadedFile {
    private String vigenere = "0";
    private String delimiter = ";";
    private String target = "txt";

    public UploadForm(){
    }
    public UploadForm(UploadedFile uploadedFile){
        super(uploadedFile);
    }

    public String getVigenere() {
        return vigenere;
    }

    public void setVigenere(String vigenere) {
        this.vigenere = vigenere;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getTarget() {
        return target;
    }

    public void setTarget(String target) {
        this.target = target;
    }
}
