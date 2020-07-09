package com.ari.project.util.transform.cast;

import com.ari.project.domain.Clients;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

public class CastToXML implements CastFile {
    @Override
    public void process(UploadForm uploadForm, List<TransformedFile> transformFile, Clients clients){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Clients.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String vigenere = uploadForm.getVigenere();
            clients.cypherVigenere(vigenere);

            StringWriter writer = new StringWriter();

            jaxbMarshaller.marshal(clients, writer);
            transformFile.add(new TransformedFile("xml",writer.toString()));
        }
        catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
    }


}
