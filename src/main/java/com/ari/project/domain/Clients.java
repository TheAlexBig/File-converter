package com.ari.project.domain;

import com.ari.project.util.Vigenere;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;
import java.util.ArrayList;
import java.util.List;

@XmlRootElement(name = "clientes")
@XmlAccessorType(XmlAccessType.FIELD)
public class Clients {
    @XmlElement(name = "cliente")
    private List<Client> clients = new ArrayList<>();

    public List<Client> getClients() {
        return clients;
    }

    public Clients(List<Client> clients) {
        this.clients = clients;
    }

    public Clients(){}

    public void setClients(List<Client> clients) {
        this.clients = clients;
    }

    public void addClient(Client client){
        clients.add(client);
    }

    public void decypherVigenere(String keyword){
        if(!keyword.isEmpty()){
            clients.forEach(c ->{
                String encrypted = c.getCreditCard();
                c.setCreditCard(Vigenere.decypherVigenere(encrypted, keyword));
            });
        }
    }
    public void cypherVigenere(String keyword){
        if(!keyword.isEmpty()){
            clients.forEach(c ->{
                String encrypted = c.getCreditCard();
                c.setCreditCard(Vigenere.cypherVigenere(encrypted, keyword));
            });
        }
    }
}
