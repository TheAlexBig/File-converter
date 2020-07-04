package com.ari.project.domain;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement(name = "cliente")
@XmlAccessorType(XmlAccessType.FIELD)
public class Client {
    @XmlElement(name = "documento")
    private String document = "";
    @XmlElement(name = "primer-nombre")
    private String name = "";
    @XmlElement(name = "apellido")
    private String lastName = "";
    @XmlElement(name = "credit-card")
    private String creditCard = "";
    @XmlElement(name = "tipo")
    private String type = "";
    @XmlElement(name = "telefono")
    private String phone = "";

    public String getDocument() {
        return document;
    }

    public void setDocument(String document) {
        this.document = document;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getCreditCard() {
        return creditCard;
    }

    public void setCreditCard(String creditCard) {
        this.creditCard = creditCard;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }
}
