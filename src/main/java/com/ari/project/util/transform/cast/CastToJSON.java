package com.ari.project.util.transform.cast;

import com.ari.project.domain.Client;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;
import org.json.JSONArray;
import org.json.JSONObject;

import java.util.List;

public class CastToJSON implements CastFile {

    @Override
    public void process(UploadForm uploadForm, List<TransformedFile> transformFile, List<Client> clientesList) {
        JSONObject json = new JSONObject();
        JSONArray clientes = new JSONArray();
        json.put("clientes", clientes);
        clientesList.forEach(c->{
            JSONObject cliente = new JSONObject();
            cliente.put("documento", c.getDocumento());
            cliente.put("primer-nombre", c.getNombre());
            cliente.put("apellido", c.getApellido());
            cliente.put("credit-card", c.getCreditCard());
            cliente.put("tipo", c.getType());
            cliente.put("telefono", c.getPhone());
            clientes.put(cliente);
        });
        String jsonString = json.toString();
        transformFile.add(new TransformedFile("json",jsonString));
    }
}
