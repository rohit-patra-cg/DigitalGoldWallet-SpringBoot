package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.TransactionHistoryDTO;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.enums.TransactionStatus;
import com.example.demo.enums.TxnHistoryTransactionType;
import com.example.demo.exception.TransactionHistoryNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;

public interface TransactionHistoryService {
	/**
	 * Get All Transaction History
	 * @return List<TransactionHistory> Collection of TransactionHistory
	 */
	List<TransactionHistory> getAllTransactionHistory();
	
	/**
	 * Get Transaction History by transaction_id
	 * @param transactionHistoryId
	 * @return TransactionHistory Object
	 * @throws TransactionHistoryNotFoundException
	 */
	TransactionHistory getTransactionHistoryByTransactionHistoryId(int transactionHistoryId) throws TransactionHistoryNotFoundException;
	
	/**
	 * Get All Transaction History by user_id
	 * @param userId
	 * @return List<TransactionHistory> Collection of TransactionHistory
	 * @throws UserNotFoundException
	 */
	List<TransactionHistory> getTransactionHistoryByUserId(int userId) throws UserNotFoundException;
	
	/**
	 * Get All Transaction History by TransactionStatus 
	 * @param transactionStatus
	 * @return List<TransactionHistory> Collection of TransactionHistory
	 */
	List<TransactionHistory> getTransactionHistoryByTransactionStatus(TransactionStatus transactionStatus);
	
	/**
	 * Get All Transaction History by TransactionType
	 * @param transactionType
	 * @return List<TransactionHistory> Collection of TransactionHistory
	 */
	List<TransactionHistory> getTransactionHistoryByTransactionType(TxnHistoryTransactionType transactionType);
	
	/**
	 * Add New Transaction History
	 * @param transactionHistoryDto
	 * @return SuccessResponse Response for successfully adding transaction history
	 * @throws UserNotFoundException
	 * @throws VendorBranchNotFoundException
	 */
	SuccessResponse createTransaction(TransactionHistoryDTO transactionHistoryDto) throws UserNotFoundException, VendorBranchNotFoundException;
}
