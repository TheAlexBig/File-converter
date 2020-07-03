package com.ari.project.util.transform.cast;

import com.ari.project.domain.Client;
import com.ari.project.domain.Clients;
import com.ari.project.domain.TransformedFile;
import com.ari.project.form.UploadForm;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.Marshaller;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
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

    public void processs(UploadForm uploadForm, List<TransformedFile> transformFile, List<Client> clientesList){
        try{
            String key = uploadForm.getVigenere();
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();
            Document document = documentBuilder.newDocument();

            Element clientes = document.createElement("clientes");
            document.appendChild(clientes);

            clientesList.forEach(c->{
                Element cliente = document.createElement("cliente");
                clientes.appendChild(cliente);

                Element documento = document.createElement("documento");
                documento.appendChild(document.createTextNode(c.getDocumento()));
                cliente.appendChild(documento);

                Element nombre = document.createElement("primer-nombre");
                nombre.appendChild(document.createTextNode(c.getNombre()));
                cliente.appendChild(nombre);

                Element apellido = document.createElement("apellido");
                apellido.appendChild(document.createTextNode(c.getApellido()));
                cliente.appendChild(apellido);

                Element card = document.createElement("credit-card");
                String encrypted = cypherVigenere(c.getCreditCard(), key);
                card.appendChild(document.createTextNode(encrypted));
                cliente.appendChild(card);

                Element tipo = document.createElement("tipo");
                tipo.appendChild(document.createTextNode(c.getType()));
                cliente.appendChild(tipo);

                Element telefono = document.createElement("telefono");
                telefono.appendChild(document.createTextNode(c.getPhone()));
                cliente.appendChild(telefono);
            });
            DOMSource domSource = new DOMSource(document);
            StringWriter writer = new StringWriter();
            StreamResult result = new StreamResult(writer);
            TransformerFactory tf = TransformerFactory.newInstance();
            Transformer transformer = tf.newTransformer();
            transformer.transform(domSource, result);

            transformFile.add(new TransformedFile("xml",writer.toString()));
        }
        catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
    }

    @Override
    public void process(UploadForm uploadForm, List<TransformedFile> transformFile, List<Client> clientesList){
        try{
            JAXBContext jaxbContext = JAXBContext.newInstance(Clients.class);
            Marshaller jaxbMarshaller = jaxbContext.createMarshaller();

            jaxbMarshaller.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, true);
            String vigenere = uploadForm.getVigenere();
            clientesList.forEach(c->{
                String credit = c.getCreditCard();
                String encrypted = cypherVigenere(credit, vigenere);
                c.setCreditCard(encrypted);
            });

            Clients clients = new Clients();
            clients.setClients(clientesList);
            StringWriter writer = new StringWriter();

            jaxbMarshaller.marshal(clients, writer);
            transformFile.add(new TransformedFile("xml",writer.toString()));
        }
        catch (Exception e){
            System.out.println("Exception: "+e.getMessage());
        }
    }


}
