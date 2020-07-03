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
    List<Client> processTxt(UploadForm uploadForm){
        String[] lines = uploadForm.getContent().split("\\r?\\n");
        List<Client> clientesList = new ArrayList<>();
        Arrays.stream(lines).forEach(c->{
            String[] line = c.split(uploadForm.getDelimiter());
            if(line.length==6){
                Client client = new Client();
                client.setDocumento(line[0]);
                client.setNombre(line[1]);
                client.setApellido(line[2]);
                client.setCreditCard(line[3]);
                client.setType(line[4]);
                client.setPhone(line[5]);
                clientesList.add(client);
            }
        });
        return clientesList;
    }

    List<Client> processXml(UploadForm uploadForm) {
        List<Client> result = new ArrayList<>();
        try {
            JAXBContext jaxbContext;
            jaxbContext = JAXBContext.newInstance(Clients.class);
            Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
            Clients clients = (Clients) jaxbUnmarshaller.unmarshal(new StringReader(uploadForm.getContent()));
            result = clients.getClients();
        }
        catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
        return result;
    }
    List<Client> processJson(UploadForm uploadForm) {
        List<Client> result = new ArrayList<>();
        JSONObject json = new JSONObject(uploadForm.getContent());
        JSONArray clientes = json.getJSONArray("clientes");
        for(int i=0;i<clientes.length();i++){
            JSONObject cliente = clientes.getJSONObject(i);
            Client c = new Client();
            c.setDocumento(cliente.get("documento").toString());
            c.setNombre(cliente.get("primer-nombre").toString());
            c.setApellido(cliente.get("apellido").toString());
            c.setCreditCard(cliente.get("credit-card").toString());
            c.setType(cliente.get("tipo").toString());
            c.setPhone(cliente.get("telefono").toString());
            result.add(c);
        }
        return result;
    }
}
