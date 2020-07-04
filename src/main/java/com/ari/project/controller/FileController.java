package com.ari.project.controller;


import com.ari.project.component.JWTTokenComponent;
import com.ari.project.domain.TransformedFile;
import com.ari.project.domain.UploadedFile;
import com.ari.project.form.UploadForm;
import com.ari.project.service.FileService;
import com.ari.project.service.FileServiceImp;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import org.springframework.web.servlet.view.RedirectView;

import java.io.IOException;
import java.util.Arrays;
import java.util.List;

@Controller
@SessionAttributes("uploadForm")
public class FileController {
    private final FileService fileService;
    private static final Logger logger = LoggerFactory.getLogger(FileController.class);

    @Autowired
    public FileController(FileServiceImp fileService){
        this.fileService = fileService;
    }

    @GetMapping("/")
    public ModelAndView listUploadedFiles(@ModelAttribute("uploadedFile") UploadedFile uploadedFile)  {
        ModelAndView modelAndView = new ModelAndView("uploadForm");
        modelAndView.addObject("uploadForm", new UploadForm(uploadedFile));
        return modelAndView;
    }

    @RequestMapping (value = "/upload", method = RequestMethod.POST)
    public RedirectView uploadFile(@RequestParam("file") MultipartFile file, RedirectAttributes attributes) throws IOException {
        UploadedFile data = fileService.prepareFile(file);
        attributes.addFlashAttribute("uploadedFile", data);
        return new RedirectView("");
    }
    @RequestMapping(value = "/token", method = RequestMethod.POST)
    public RedirectView transformToken(@RequestParam("jwtData") String jwtData, RedirectAttributes attributes){
        UploadedFile data = fileService.prepareJwt(jwtData);
        attributes.addFlashAttribute("uploadedFile", data);
        return new RedirectView("");
    }

    @RequestMapping(value = "/action/{type}", method = RequestMethod.POST)
    public String transformFile(Model model, @ModelAttribute("uploadForm") UploadForm uploadForm, @PathVariable String type) {
        if(uploadForm.getType().equals(type)){
            List<TransformedFile> transformFile = fileService.process(uploadForm);
            model.addAttribute("result", transformFile);
            return "resultForm";
        }
        return "redirect:/";
    }


    @ExceptionHandler({Exception.class})
    public String catchCrash(Model model, RuntimeException error) {
        model.addAttribute("error", error);
        return "errorForm";
    }
}
