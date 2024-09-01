package com.example.demo.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.Vendor;

@Repository
public interface VendorRepository extends JpaRepository<Vendor, Integer> {

	Optional<Vendor> findByVendorName(String name);

	Optional<Vendor> findByContactEmail(String email);
}
