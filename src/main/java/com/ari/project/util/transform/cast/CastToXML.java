package com.ari.project.util.transform.cast;

import com.ari.project.domain.Client;
import com.ari.project.domain.Clients;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import java.io.StringWriter;
import java.util.List;

public class CastToXML implements CastFile {

    private String generateKey(String entry, String keyword)
    {
        StringBuilder key = new StringBuilder(keyword);
        int x = entry.length();
        for (int i = 0; ; i++)
        {
            if (x == i)
                i = 0;
            if (key.length() == entry.length())
                break;
            key.append(key.charAt(i));
        }
        return key.toString();
    }

    private String cypherVigenere(String entry, String keyword){
        String key = generateKey(entry, keyword);
        StringBuilder result = new StringBuilder(0);
        for (int i = 0; i < entry.length(); i++)
        {
            int x = (entry.charAt(i) + key.charAt(i)) %26;
            x += 'A';

            result.append((char)(x));
        }
        return result.toString();
    }

    @Override
    public void process(UploadForm uploadForm, List<TransformedFile> transformFile, Clients clients){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Clients.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String vigenere = uploadForm.getVigenere();
            clients.getClients().forEach(c->{
                String credit = c.getCreditCard();
                String encrypted = cypherVigenere(credit, vigenere);
                c.setCreditCard(encrypted);
            });

            StringWriter writer = new StringWriter();

            jaxbMarshaller.marshal(clients, writer);
            transformFile.add(new TransformedFile("xml",writer.toString()));
        }
        catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
    }


}
