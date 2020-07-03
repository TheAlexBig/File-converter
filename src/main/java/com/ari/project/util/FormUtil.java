package com.ari.project.util;

import com.ari.project.domain.UploadedFile;
import com.ari.project.form.UploadForm;
import org.springframework.ui.Model;

import java.util.Optional;

public class FormUtil {
    public static void addTypeAttribute(Model model, UploadedFile uploadedFile){
        UploadForm uploadForm = new UploadForm();
        model.addAttribute("uploadForm", uploadForm);
    }
}
