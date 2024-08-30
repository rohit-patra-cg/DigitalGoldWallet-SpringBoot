package com.example.demo.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.TransactionHistory;

@Repository
public interface TransactionHistoryRepository extends JpaRepository<TransactionHistory, Integer> {
	@Query("FROM TransactionHistory th WHERE th.user.userId = :userId")
	List<TransactionHistory> findAllByUserId(int userId);
}
