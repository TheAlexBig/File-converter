package com.ari.project.util.transform;

import com.ari.project.domain.Client;
import com.ari.project.domain.Clients;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;

import java.util.List;

public abstract class TransformFile {
    private static final ClientExtractor clientExtractor = new ClientExtractor();
    private static final CastHub castHub = new CastHub();

    public static void toOtherFormats(UploadForm uploadForm, List<TransformedFile> transformFile){
        Clients clients = defineClients(uploadForm, transformFile);
        transformToTarget(uploadForm, transformFile, clients);
    }

    private static void transformToTarget(UploadForm uploadForm, List<TransformedFile> transformFile, Clients clients){
        switch (uploadForm.getTarget()){
            case "json":
                castHub.forJson(uploadForm, transformFile, clients);
                break;
            case "xml":
                castHub.forXml(uploadForm, transformFile, clients);
                break;
            case "txt":
                castHub.forTxt(uploadForm, transformFile, clients);
                break;
            case "jwt":
                castHub.forJwt(uploadForm, transformFile);
                break;
        }
    }

    private static Clients defineClients(UploadForm uploadForm, List<TransformedFile> transformFile){
        Clients clients = new Clients();
        switch (uploadForm.getType()){
            case "json":
                clients = clientExtractor.processJson(uploadForm);
                break;
            case "xml":
                clients = clientExtractor.processXml(uploadForm);
                break;
            case "txt":
                clients = clientExtractor.processTxt(uploadForm);
                break;
        }
        return clients;
    }
}
