package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.PhysicalGoldTransactionDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.exception.PhysicalGoldTransactionNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;

public interface PhysicalGoldTransactionService {
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactions();
	PhysicalGoldTransaction getPhysicalGoldTransactionById(int physicalGoldTransactionId) throws PhysicalGoldTransactionNotFoundException;
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByUserId(int userId) throws UserNotFoundException;
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByBranchId(int branchId) throws VendorBranchNotFoundException;
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryCity(String city);
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryState(String state);
	SuccessResponse createPhysicalGoldTransaction(PhysicalGoldTransactionDTO dto) throws UserNotFoundException, AddressNotFoundException, VendorBranchNotFoundException;
	SuccessResponse updatePhysicalGoldTransaction(int transactionId, PhysicalGoldTransactionDTO dto) throws PhysicalGoldTransactionNotFoundException, VendorBranchNotFoundException, UserNotFoundException, AddressNotFoundException;
}
