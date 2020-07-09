package com.ari.project.util.transform;

import com.ari.project.domain.Client;
import com.ari.project.domain.Clients;
import com.ari.project.form.UploadForm;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Unmarshaller;
import java.io.StringReader;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

class ClientExtractor {
    Clients processTxt(UploadForm uploadForm){
        String[] lines = uploadForm.getContent().split("\\r?\\n");
        Clients clients = new Clients();
        Arrays.stream(lines).forEach(c->{
            String[] line = c.split(uploadForm.getDelimiter());
            if(line.length==6){
                Client client = new Client();
                client.setDocument(line[0]);
                client.setName(line[1]);
                client.setLastName(line[2]);
                client.setCreditCard(line[3]);
                client.setType(line[4]);
                client.setPhone(line[5]);
                clients.addClient(client);
            }
        });
        return clients;
    }

    Clients processXml(UploadForm uploadForm) {
        Clients clients = new Clients();
        try {
            JAXBContext jaxbContext = JAXBContext.newInstance(Clients.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            clients = (Clients) jaxbUnmarshaller.unmarshal(new StringReader(uploadForm.getContent()));
            String vigenere = uploadForm.getVigenere();
            clients.decypherVigenere(vigenere);
        }
        catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
        return clients;
    }
    Clients processJson(UploadForm uploadForm) {
        Clients clients = new Clients();
        JSONObject json = new JSONObject(uploadForm.getContent());
        JSONArray clientes = json.getJSONArray("clientes");
        for(int i=0;i<clientes.length();i++){
            JSONObject cliente = clientes.getJSONObject(i);
            Client c = new Client();
            c.setDocument(cliente.get("documento").toString());
            c.setName(cliente.get("primer-nombre").toString());
            c.setLastName(cliente.get("apellido").toString());
            c.setCreditCard(cliente.get("credit-card").toString());
            c.setType(cliente.get("tipo").toString());
            c.setPhone(cliente.get("telefono").toString());
            clients.addClient(c);
        }
        return clients;
    }
}
