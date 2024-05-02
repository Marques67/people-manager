package com.example.peoplemanager.services;

import com.example.peoplemanager.dto.AddressDTO;
import com.example.peoplemanager.dto.PersonDTO;
import com.example.peoplemanager.entities.Address;
import com.example.peoplemanager.entities.Person;
import com.example.peoplemanager.repositories.PersonRepository;
import com.example.peoplemanager.services.exceptions.MultipleMainAdressesException;
import com.example.peoplemanager.services.exceptions.ResourceNotFoundException;
import com.example.peoplemanager.utils.UtilAddress;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class PersonService {

    @Autowired
    private PersonRepository personRepository;

    @Autowired
    private AddressService addressService;

    public List<PersonDTO> findAllPeople() {
        List<Person> people = personRepository.findAll();
        return people.stream().map(PersonDTO::new).collect(Collectors.toList());
    }

    public Person findPersonById(Long id) {
        Optional<Person> person = personRepository.findById(id);
        if (person.isPresent()) {
            return person.get();
        } else {
            throw new ResourceNotFoundException("ID not found " + id);
        }
    }

    public PersonDTO findById(Long id) {
        Person person = findPersonById(id);
        return new PersonDTO(person);
    }

    public PersonDTO insertNewPerson(PersonDTO personDTO) {
        Person person = new Person();
        copyDtoToEntity(personDTO, person);
        person = personRepository.save(person);

        return new PersonDTO(person);
    }

    public PersonDTO updatePerson(Long id, PersonDTO personDTO) {
        try {
            Person person = findPersonById(id);
            copyDtoToEntity(personDTO, person);
            person = personRepository.save(person);
            return new PersonDTO(person);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID not found " + id);
        }
    }

    private void copyDtoToEntity(PersonDTO personDTO, Person person) {
        person.setFullName(personDTO.getFullName());
        person.setBirthday(personDTO.getBirthday());

        boolean haveMainAddress = validateMainAddress(personDTO);

        if (haveMainAddress) {
            UtilAddress.updateMainAddress(person);
        }

        for (AddressDTO addressDTO : personDTO.getAddress()) {
            Address address = new Address();
            address.setStreet(addressDTO.getStreet());
            address.setCep(addressDTO.getCep());
            address.setNumber(addressDTO.getNumber());
            address.setCity(addressDTO.getCity());
            address.setState(addressDTO.getState());
            address.setPerson(person);
            address.setMain(addressDTO.getMain());
            person.getAddress().add(address);
        }
    }

    private boolean validateMainAddress(PersonDTO personDTO) {
        List<AddressDTO> addressList = personDTO.getAddress();
        int isMain = 0;

        for (AddressDTO addressDTO :  addressList) {
            if (addressDTO.getMain()) {
                isMain++;
            }
        }

        if (isMain > 1) {
            throw new MultipleMainAdressesException("Multiple main addresses found. Only one main address is allowed.");
        } else return isMain == 1;
    }
}
