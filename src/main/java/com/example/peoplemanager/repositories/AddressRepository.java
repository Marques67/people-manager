package com.example.peoplemanager.repositories;

import com.example.peoplemanager.entities.Address;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AddressRepository extends JpaRepository<Address, Long> {

    public List<Address> findAllByPersonId(Long personId);
}
