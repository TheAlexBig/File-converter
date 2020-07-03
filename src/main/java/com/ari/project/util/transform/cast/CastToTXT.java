package com.ari.project.util.transform.cast;

import com.ari.project.domain.Client;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;

import java.util.List;

public class CastToTXT implements CastFile{
    @Override
    public void process(UploadForm uploadForm, List<TransformedFile> transformFile, List<Client> clientesList) {
        StringBuilder result = new StringBuilder(0);
        String delimiter = uploadForm.getDelimiter();
        clientesList.forEach(c->{
            result.append(c.getDocumento())
                    .append(delimiter)
                    .append(c.getNombre())
                    .append(delimiter)
                    .append(c.getApellido())
                    .append(delimiter)
                    .append(c.getCreditCard())
                    .append(delimiter)
                    .append(c.getType())
                    .append(delimiter)
                    .append(c.getPhone())
                    .append(System.getProperty("line.separator"));
        });
        if(result.length()>0){
            transformFile.add(new TransformedFile("txt", result.toString()));
        }
    }
}
