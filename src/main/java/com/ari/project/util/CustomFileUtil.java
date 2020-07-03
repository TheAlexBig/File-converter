package com.ari.project.util;

import org.springframework.web.multipart.MultipartFile;

import java.io.ByteArrayInputStream;
import java.io.IOException;

import java.nio.charset.StandardCharsets;
import java.util.Objects;

public class CustomFileUtil {
    public static String[] fileExtAndName(MultipartFile file) throws IOException {
        return Objects.requireNonNull(file.getOriginalFilename()).split("[.]");
    }
    public static String fileContent(MultipartFile file) throws IOException{
        return new String(file.getBytes());
    }
}
