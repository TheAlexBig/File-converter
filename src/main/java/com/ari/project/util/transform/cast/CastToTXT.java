package com.ari.project.util.transform.cast;

import com.ari.project.domain.Client;
import com.ari.project.domain.Clients;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;

import java.util.List;

public class CastToTXT implements CastFile{
    @Override
    public void process(UploadForm uploadForm, List<TransformedFile> transformFile, Clients clients) {
        StringBuilder result = new StringBuilder(0);
        String delimiter = uploadForm.getDelimiter();
        clients.getClients().forEach(c-> result.append(c.getDocument())
                    .append(delimiter)
                    .append(c.getName())
                    .append(delimiter)
                    .append(c.getLastName())
                    .append(delimiter)
                    .append(c.getCreditCard())
                    .append(delimiter)
                    .append(c.getType())
                    .append(delimiter)
                    .append(c.getPhone())
                    .append(System.getProperty("line.separator")));
        if(result.length()>0){
            transformFile.add(new TransformedFile("txt", result.toString()));
        }
    }
}
