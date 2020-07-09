package com.ari.project.util.identify.type;

import com.ari.project.controller.FileController;
import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.ResourceUtils;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TypeJSON implements TypeFile {
    private static final Logger logger = LoggerFactory.getLogger(TypeJSON.class);
    public boolean validateSchema(String entry){
        try{
            File file = ResourceUtils.getFile("classpath:schema/json-entry.json");
            JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileInputStream(file)));
            JSONObject jsonSubject = new JSONObject(new JSONTokener(new StreamSource(new ByteArrayInputStream(entry.getBytes(StandardCharsets.UTF_8))).getInputStream()));

            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        }
        catch (IOException e){
            System.out.println("File Exception: "+e.getMessage());
            return false;
        }
        catch (Exception e){
            System.out.println("JSON format Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}
