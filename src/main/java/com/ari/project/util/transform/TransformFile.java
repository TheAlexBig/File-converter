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
        Clients clients;
        switch (uploadForm.getType()){
            case "json":
                clients = clientExtractor.processJson(uploadForm);
                castHub.forJson(uploadForm, transformFile, clients);
                break;
            case "xml":
                clients = clientExtractor.processXml(uploadForm);
                castHub.forXml(uploadForm, transformFile, clients);
                break;
            case "txt":
                clients = clientExtractor.processTxt(uploadForm);
                castHub.forTxt(uploadForm, transformFile, clients);
                break;
        }
    }
}
