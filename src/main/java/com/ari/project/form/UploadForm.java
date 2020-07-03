package com.ari.project.form;

import com.ari.project.domain.UploadedFile;

public class UploadForm extends UploadedFile {
    private String destination = "";
    private String delimiter = "";
    private String vigenere = "";

    public UploadForm(){
    }
    public UploadForm(UploadedFile uploadedFile){
        super(uploadedFile);
    }

    public String getDestination() {
        return destination;
    }

    public void setDestination(String destination) {
        this.destination = destination;
    }

    public String getDelimiter() {
        return delimiter;
    }

    public void setDelimiter(String delimiter) {
        this.delimiter = delimiter;
    }

    public String getVigenere() {
        return vigenere;
    }

    public void setVigenere(String vigenere) {
        this.vigenere = vigenere;
    }
}
