package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PhysicalGoldTransaction;

@Repository
public interface PhysicalGoldTransactionRepository extends JpaRepository<PhysicalGoldTransaction, Integer> {
	
	@Query("FROM PhysicalGoldTransaction p WHERE p.user.userId = :userId")
	List<PhysicalGoldTransaction> findAllPhysicalGoldTransactionsByUserId(int userId);
}
