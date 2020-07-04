package com.ari.project.util.transform.cast;

import com.ari.project.domain.Client;
import com.ari.project.domain.Clients;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CastToJSON implements CastFile {

    @Override
    public void process(UploadForm uploadForm, List<TransformedFile> transformFile, Clients clients) {
        JSONObject json = new JSONObject();
        JSONArray clientes = new JSONArray();
        json.put("clientes", clientes);
        clients.getClients().forEach(c->{
            JSONObject cliente = new JSONObject();
            cliente.put("documento", c.getDocument());
            cliente.put("primer-nombre", c.getName());
            cliente.put("apellido", c.getLastName());
            cliente.put("credit-card", c.getCreditCard());
            cliente.put("tipo", c.getType());
            cliente.put("telefono", c.getPhone());
            clientes.put(cliente);
        });
        String jsonString = json.toString();
        transformFile.add(new TransformedFile("json",jsonString));
    }
}
