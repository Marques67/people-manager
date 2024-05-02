package com.example.peoplemanager.utils;

import com.example.peoplemanager.entities.Address;
import com.example.peoplemanager.entities.Person;

import java.util.List;
import java.util.Optional;

public class UtilAddress {

    public static void updateMainAddress(Person person) {
        Optional<Address> mainAddress = person.getAddress().stream().filter(Address::getMain).findFirst();

        mainAddress.ifPresent(address -> address.setMain(false));
    }

}
