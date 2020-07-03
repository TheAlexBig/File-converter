package com.ari.project.util.transform;

import com.ari.project.domain.Client;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;

import java.util.List;

public abstract class TransformFile {
    private static final ClientExtractor clientExtractor = new ClientExtractor();
    private static final CastHub castHub = new CastHub();

    public static void toOtherFormats(UploadForm uploadForm, List<TransformedFile> transformFile){
        List<Client> clientList;
        switch (uploadForm.getType()){
            case "json":
                clientList = clientExtractor.processJson(uploadForm);
                castHub.forJson(uploadForm, transformFile, clientList);
                break;
            case "xml":
                clientList = clientExtractor.processXml(uploadForm);
                castHub.forXml(uploadForm, transformFile, clientList);
                break;
            case "txt":
                clientList = clientExtractor.processTxt(uploadForm);
                castHub.forTxt(uploadForm, transformFile, clientList);
                break;
        }
    }
}
