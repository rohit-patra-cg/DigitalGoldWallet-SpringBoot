package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Address;

@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {
}
