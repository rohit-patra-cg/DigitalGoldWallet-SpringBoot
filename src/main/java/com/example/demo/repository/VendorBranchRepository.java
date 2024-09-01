package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VendorBranch;

@Repository
public interface VendorBranchRepository extends JpaRepository<VendorBranch, Integer> {

	List<VendorBranch> findByBranchId(int branchId);

	@Query("FROM VendorBranch vb WHERE vb.address.city = :city")
	List<VendorBranch> findByCity(String city);

	@Query("FROM VendorBranch vb WHERE vb.address.state = :state")
	List<VendorBranch> findByState(String state);

	@Query("FROM VendorBranch vb WHERE vb.address.country = :country")
	List<VendorBranch> findByCountry(String country);
}
