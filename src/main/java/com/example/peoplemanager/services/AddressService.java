package com.example.peoplemanager.services;

import com.example.peoplemanager.dto.AddressDTO;
import com.example.peoplemanager.entities.Address;
import com.example.peoplemanager.entities.Person;
import com.example.peoplemanager.repositories.AddressRepository;
import com.example.peoplemanager.services.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityNotFoundException;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AddressService {

    @Autowired
    private PersonService personService;

    @Autowired
    private AddressRepository addressRepository;


    public List<AddressDTO> findAllAddressByPerson(Long personId) {
        List<Address> addresses = addressRepository.findAllByPersonId(personId);
        return addresses.stream().map(AddressDTO::new).collect(Collectors.toList());
    }

    public Address findAddressById(Long personId, Long addressId) {
        Person person = personService.findPersonById(personId);
        Optional<Address> addressOp = person.getAddress().stream().filter(address -> address.getId().equals(addressId)).findFirst();
        if (addressOp.isPresent()) {
            return addressOp.get();
        } else {
            throw new ResourceNotFoundException("ID not found " + addressId);
        }
    }

    public AddressDTO findById(Long personId, Long addressId) {
        Address address = findAddressById(personId, addressId);
        return new AddressDTO(address);
    }

    @Transactional
    public AddressDTO insertNewAddress(Long personId, AddressDTO addressDTO) {
        Person person = personService.findPersonById(personId);
        Address address = new Address();
        copyDtoToEntity(addressDTO, address);
        address.setPerson(person);
        person.getAddress().add(address);
        address = addressRepository.save(address);

        return new AddressDTO(address, person);
    }


    public AddressDTO updateAddress(Long personId, Long addressId, AddressDTO addressDTO) {
        try {
            Person person = personService.findPersonById(personId);
            Address address = person.getAddress().stream().filter(a -> a.getId().equals(addressId)).findFirst().get();
            copyDtoToEntity(addressDTO, address);
            addressRepository.save(address);
            return new AddressDTO(address, person);
        }
        catch (EntityNotFoundException e) {
            throw new ResourceNotFoundException("ID not found " + addressId);
        }
    }

    private void copyDtoToEntity(AddressDTO addressDTO, Address address) {
        address.setStreet(addressDTO.getStreet());
        address.setCep(addressDTO.getCep());
        address.setNumber(addressDTO.getNumber());
        address.setCity(addressDTO.getCity());
        address.setState(addressDTO.getState());
    }
}
