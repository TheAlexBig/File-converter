package com.ari.project.service;


import com.ari.project.component.JWTTokenComponent;
import com.ari.project.domain.TransformedFile;
import com.ari.project.domain.UploadedFile;
import com.ari.project.form.UploadForm;
import com.ari.project.util.CustomFileUtil;
import com.ari.project.util.identify.TypeIdentifier;
import com.ari.project.util.transform.TransformFile;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Service
public class FileServiceImp implements FileService {


    @Override
    public UploadedFile prepareFile(MultipartFile file) throws IOException {
        UploadedFile uploadedFile = new UploadedFile();
        String[] result = CustomFileUtil.fileExtAndName(file);
        String content = new String(file.getBytes());

        uploadedFile.setFilename(result[0]);
        uploadedFile.setExt(result[1]);

        uploadedFile.setType(TypeIdentifier.identifyType(content));
        uploadedFile.setContent(CustomFileUtil.fileContent(file));
        uploadedFile.setUploaded(true);
        return uploadedFile;
    }

    @Override
    public UploadedFile prepareJwt(String jwtToken) {
        UploadedFile uploadedFile = new UploadedFile();
        try{
            Claims data = JWTTokenComponent.retrieveClaims(jwtToken);
            String content = data.get("content", String.class);
            String filename = data.get("filename", String.class);
            String ext = data.get("ext", String.class);
            String type = data.get("type", String.class);

            uploadedFile.setContent(content);
            uploadedFile.setFilename(filename);
            uploadedFile.setType(type);
            uploadedFile.setExt(ext);
            uploadedFile.setUploaded(true);
        }
        catch (ExpiredJwtException | MalformedJwtException | UnsupportedJwtException e){
            System.out.println("Exception: "+e.getMessage());
        }
        return uploadedFile;
    }

    @Override
    public List<TransformedFile> process(UploadForm uploadForm) {
        List<TransformedFile> transformedFile = new ArrayList<>();
        TransformFile.toOtherFormats(uploadForm, transformedFile);
        return transformedFile;
    }


}
