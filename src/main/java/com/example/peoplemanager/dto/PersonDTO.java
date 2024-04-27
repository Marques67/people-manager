package com.example.peoplemanager.dto;

import com.example.peoplemanager.entities.Person;

import java.io.Serializable;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;

public class PersonDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    private Long id;

    private String fullName;

    private Instant birthday;

    private List<AddressDTO> address = new ArrayList<>();

    public PersonDTO() {}

    public PersonDTO(Long id, String fullName, Instant birthday, List<AddressDTO> address) {
        this.id = id;
        this.fullName = fullName;
        this.birthday = birthday;
        this.address = address;
    }

    public PersonDTO(Person person) {
        this.id = person.getId();
        this.fullName = person.getFullName();
        this.birthday = person.getBirthday();
        person.getAddress().forEach(address -> this.address.add(new AddressDTO(address)));
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public Instant getBirthday() {
        return birthday;
    }

    public void setBirthday(Instant birthday) {
        this.birthday = birthday;
    }

    public List<AddressDTO> getAddress() {
        return address;
    }

    public void setAddress(List<AddressDTO> address) {
        this.address = address;
    }
}
