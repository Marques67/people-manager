package com.example.peoplemanager.controllers;

import com.example.peoplemanager.dto.AddressDTO;
import com.example.peoplemanager.services.AddressService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;

@RestController
@RequestMapping(value = "/address")
public class AddressController {

    @Autowired
    private AddressService service;

    @GetMapping(value = "/{personId}")
    public ResponseEntity<List<AddressDTO>> findAllAddressByPerson(@PathVariable Long personId) {
        List<AddressDTO> address = service.findAllAddressByPerson(personId);
        return ResponseEntity.ok().body(address);
    }

    @GetMapping(value = "/{personId}/{addressId}")
    public ResponseEntity<AddressDTO> findById(@PathVariable Long personId, @PathVariable Long addressId) {
        AddressDTO dto = service.findById(personId, addressId);
        return ResponseEntity.ok().body(dto);
    }

    @PostMapping(value = "/{personId}")
    public ResponseEntity<AddressDTO> insertNewAddress(@PathVariable Long personId, @RequestBody AddressDTO dto) {
        dto = service.insertNewAddress(personId, dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{personId}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }

    @PutMapping(value = "/{personId}/{addressId}")
    public ResponseEntity<AddressDTO> updateAddress(@PathVariable Long personId, @PathVariable Long addressId, @RequestBody AddressDTO dto) {
        dto = service.updateAddress(personId, addressId, dto);
        return ResponseEntity.ok().body(dto);
    }
}
