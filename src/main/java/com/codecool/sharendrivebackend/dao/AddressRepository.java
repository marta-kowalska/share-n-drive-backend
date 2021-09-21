package com.codecool.sharendrivebackend.dao;

import com.codecool.sharendrivebackend.model.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, Long> {
}
