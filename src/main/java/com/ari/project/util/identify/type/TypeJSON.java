package com.ari.project.util.identify.type;

import org.everit.json.schema.Schema;
import org.everit.json.schema.loader.SchemaLoader;
import org.json.JSONObject;
import org.json.JSONTokener;
import org.springframework.util.ResourceUtils;

import javax.xml.transform.stream.StreamSource;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.nio.charset.StandardCharsets;

public class TypeJSON implements TypeFile {
    public boolean validateSchema(String entry){
        try{
            File file = ResourceUtils.getFile("classpath:schema/json-entry.json");
            JSONObject jsonSchema = new JSONObject(new JSONTokener(new FileInputStream(file)));
            JSONObject jsonSubject = new JSONObject(new JSONTokener(new StreamSource(new ByteArrayInputStream(entry.getBytes(StandardCharsets.UTF_8))).getInputStream()));

            Schema schema = SchemaLoader.load(jsonSchema);
            schema.validate(jsonSubject);
        }
        catch (Exception e ){
            System.out.println("Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}
