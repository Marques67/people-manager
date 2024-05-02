package com.example.peoplemanager.dto;

import com.example.peoplemanager.entities.Address;
import com.example.peoplemanager.entities.Person;

import java.io.Serializable;

public class AddressDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String street;

    private String cep;

    private Integer number;

    private String city;

    private String state;

    private PersonDTO personDTO;

    private boolean isMain;

    public AddressDTO() {}

    public AddressDTO(Long id, String street, String cep, Integer number, String city, String state, Boolean isMain) {
        this.id = id;
        this.street = street;
        this.cep = cep;
        this.number = number;
        this.city = city;
        this.state = state;
        this.isMain = isMain;
    }

    public AddressDTO(Address address) {
        this.id = address.getId();
        this.street = address.getStreet();
        this.cep = address.getCep();
        this.number = address.getNumber();
        this.city = address.getCity();
        this.state = address.getState();
        this.isMain = address.getMain();
    }

    public AddressDTO(Address address, Person person) {
        this(address);
        this.personDTO = new PersonDTO(person);
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getStreet() {
        return street;
    }

    public void setStreet(String street) {
        this.street = street;
    }

    public String getCep() {
        return cep;
    }

    public void setCep(String cep) {
        this.cep = cep;
    }

    public Integer getNumber() {
        return number;
    }

    public void setNumber(Integer number) {
        this.number = number;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public boolean getMain() {
        return isMain;
    }

    public void setMain(boolean main) {
        isMain = main;
    }
}
