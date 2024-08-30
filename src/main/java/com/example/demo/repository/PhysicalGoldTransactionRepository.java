package com.example.demo.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.PhysicalGoldTransaction;

@Repository
public interface PhysicalGoldTransactionRepository extends JpaRepository<PhysicalGoldTransaction, Integer> {
}
