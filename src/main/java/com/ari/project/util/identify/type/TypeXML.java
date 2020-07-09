package com.ari.project.util.identify.type;

import org.springframework.util.ResourceUtils;
import org.xml.sax.SAXException;

import javax.xml.XMLConstants;
import javax.xml.transform.stream.StreamSource;
import javax.xml.validation.Schema;
import javax.xml.validation.SchemaFactory;
import javax.xml.validation.Validator;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class TypeXML implements TypeFile {
    public boolean validateSchema(String entry){
        try {
            SchemaFactory factory = SchemaFactory.newInstance(XMLConstants.W3C_XML_SCHEMA_NS_URI);
            File file = ResourceUtils.getFile("classpath:schema/xml-entry.xsd");
            Schema schema = factory.newSchema(file);
            Validator validator = schema.newValidator();
            validator.validate(new StreamSource(new ByteArrayInputStream(entry.getBytes(StandardCharsets.UTF_8))));
        }
        catch (IOException e){
            System.out.println("File Exception: "+e.getMessage());
            return false;
        }
        catch (SAXException e){
            System.out.println("XML format Exception: "+e.getMessage());
            return false;
        }
        return true;
    }
}
