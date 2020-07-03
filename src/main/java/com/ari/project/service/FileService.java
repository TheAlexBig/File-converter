package com.ari.project.service;

import com.ari.project.domain.TransformedFile;
import com.ari.project.domain.UploadedFile;
import com.ari.project.form.UploadForm;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;


public interface FileService {
    UploadedFile prepareFile(MultipartFile file) throws IOException;
    UploadedFile prepareJwt(String jwtToken);
    List<TransformedFile> process(UploadForm uploadForm);

}
