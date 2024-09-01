package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.exception.PhysicalGoldTransactionNotFoundException;
import com.example.demo.exception.UserNotFoundException;

public interface PhysicalGoldTransactionService {
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactions();
	PhysicalGoldTransaction getPhysicalGoldTransactionById(int physicalGoldTransactionId) throws PhysicalGoldTransactionNotFoundException;
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByUserId(int userId) throws UserNotFoundException;
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByBranchId(int branchId);
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryCity(String city);
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryState(String state);
}
