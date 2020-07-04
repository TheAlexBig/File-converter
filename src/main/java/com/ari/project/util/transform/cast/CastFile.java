package com.ari.project.util.transform.cast;

import com.ari.project.domain.Client;
import com.ari.project.domain.Clients;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;

import java.util.List;

public interface CastFile {
    void process(UploadForm uploadForm, List<TransformedFile> transformFile, Clients clients);
}
