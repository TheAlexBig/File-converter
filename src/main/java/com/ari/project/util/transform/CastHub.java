package com.ari.project.util.transform;

import com.ari.project.component.JWTTokenComponent;
import com.ari.project.domain.Client;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;
import com.ari.project.util.transform.cast.CastFile;
import com.ari.project.util.transform.cast.CastToJSON;
import com.ari.project.util.transform.cast.CastToTXT;
import com.ari.project.util.transform.cast.CastToXML;
import org.springframework.stereotype.Component;

import java.util.List;
@Component
class CastHub {
    private final CastFile castToXML = new CastToXML();
    private final CastFile castToJSON = new CastToJSON();
    private final CastFile castToTXT = new CastToTXT();

    void forTxt(UploadForm uploadForm, List<TransformedFile> transformFile, List<Client> clientesList){
        castToXML.process(uploadForm, transformFile, clientesList);
        castToJSON.process(uploadForm, transformFile, clientesList);
        JWTTokenComponent.generateToken(uploadForm, transformFile);
    }

    void forXml(UploadForm uploadForm, List<TransformedFile> transformFile, List<Client> clientesList){
        castToTXT.process(uploadForm, transformFile, clientesList);
        castToJSON.process(uploadForm, transformFile, clientesList);
        JWTTokenComponent.generateToken(uploadForm, transformFile);
    }

    void forJson(UploadForm uploadForm, List<TransformedFile> transformFile, List<Client> clientesList){
        castToTXT.process(uploadForm, transformFile, clientesList);
        castToXML.process(uploadForm, transformFile, clientesList);
        JWTTokenComponent.generateToken(uploadForm, transformFile);
    }
}
