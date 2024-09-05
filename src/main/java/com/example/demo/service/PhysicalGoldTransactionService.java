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
	/**
	 * Get All Physical Gold Transactions
	 * @return List<PhysicalGoldTransaction> Collection of PhysicalGoldTransactions
	 */
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactions();
	
	/**
	 * Get Physical Gold Transactions by transaction_id
	 * @param physicalGoldTransactionId
	 * @return PhysicalGoldTransaction Object
	 * @throws PhysicalGoldTransactionNotFoundException
	 */
	PhysicalGoldTransaction getPhysicalGoldTransactionById(int physicalGoldTransactionId) throws PhysicalGoldTransactionNotFoundException;
	
	/**
	 * Get Physical Gold Transactions by user_id
	 * @param userId
	 * @return List<PhysicalGoldTransaction> Collection of PhysicalGoldTransactions
	 * @throws UserNotFoundException
	 */
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByUserId(int userId) throws UserNotFoundException;
	
	/**
	 * Get Physical Gold Transactions by branch_id
	 * @param branchId
	 * @return List<PhysicalGoldTransaction> Collection of PhysicalGoldTransactions
	 * @throws VendorBranchNotFoundException
	 */
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByBranchId(int branchId) throws VendorBranchNotFoundException;
	
	/**
	 * Get All Physical Gold Transactions by delivery city 
	 * @param city
	 * @return List<PhysicalGoldTransaction> Collection of PhysicalGoldTransactions 
	 */
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryCity(String city);
	
	/**
	 * Get All Physical Gold Transactions by delivery state
	 * @param state
	 * @return List<PhysicalGoldTransaction> Collection of PhysicalGoldTransactions
	 */
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryState(String state);
	
	/**
	 * Add New Physical Gold Transactions
	 * @param dto
	 * @return SuccessResponse Response for successfully adding new physical gold transaction
	 * @throws UserNotFoundException
	 * @throws AddressNotFoundException 
	 * @throws VendorBranchNotFoundException
	 */
	SuccessResponse createPhysicalGoldTransaction(PhysicalGoldTransactionDTO dto) throws UserNotFoundException, AddressNotFoundException, VendorBranchNotFoundException;
	
	/**
	 * Update Physical Gold Transactions by transaction_id
	 * @param transactionId, PhysicalGoldTransactionDTO
	 * @return SuccessResponse Response for successfully updating new physical gold transaction
	 * @throws PhysicalGoldTransactionNotFoundException
	 * @throws VendorBranchNotFoundException
	 * @throws UserNotFoundException 
	 * @throws AddressNotFoundException
	 */
	SuccessResponse updatePhysicalGoldTransaction(int transactionId, PhysicalGoldTransactionDTO dto) throws PhysicalGoldTransactionNotFoundException, VendorBranchNotFoundException, UserNotFoundException, AddressNotFoundException;
}
