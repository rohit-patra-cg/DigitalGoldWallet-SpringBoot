package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.VirtualGoldHolding;

@Repository
public interface VirtualGoldHoldingRepository extends JpaRepository<VirtualGoldHolding, Integer> {

	@Query("FROM VirtualGoldHolding v WHERE v.user.userId = :userId")
	List<VirtualGoldHolding> findAllVirtualGoldHoldingByUserId(int userId);
	
	@Query("FROM VirtualGoldHolding v WHERE v.user.userId = :userId AND v.branch.vendor.vendorId = :vendorId")
	List<VirtualGoldHolding> findAllVirtualGoldHoldingByUserIdAndVendorId(int userId, int vendorId);

}
