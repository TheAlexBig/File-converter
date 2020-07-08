package com.ari.project.util.transform;

import com.ari.project.component.JWTTokenComponent;
import com.ari.project.domain.Client;
import com.ari.project.domain.Clients;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;
import com.ari.project.util.transform.cast.CastFile;
import com.ari.project.util.transform.cast.CastToJSON;
import com.ari.project.util.transform.cast.CastToTXT;
import com.ari.project.util.transform.cast.CastToXML;

import java.util.List;

class CastHub {
    private final static CastFile castToXML = new CastToXML();
    private final static CastFile castToJSON = new CastToJSON();
    private final static CastFile castToTXT = new CastToTXT();

    void forTxt(UploadForm uploadForm, List<TransformedFile> transformFile, Clients clients){
        castToTXT.process(uploadForm, transformFile, clients);
    }

    void forXml(UploadForm uploadForm, List<TransformedFile> transformFile, Clients clients){
        castToXML.process(uploadForm, transformFile, clients);
    }

    void forJson(UploadForm uploadForm, List<TransformedFile> transformFile, Clients clients){
        castToJSON.process(uploadForm, transformFile, clients);
    }

    void forJwt(UploadForm uploadForm, List<TransformedFile> transformFile){
        JWTTokenComponent.generateToken(uploadForm, transformFile);
    }
}
