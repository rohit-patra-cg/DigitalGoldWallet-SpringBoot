package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.TransactionHistoryDTO;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.enums.TransactionStatus;
import com.example.demo.enums.TxnHistoryTransactionType;
import com.example.demo.exception.TransactionHistoryNotFoundException;
import com.example.demo.exception.TransactionTypeNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;

public interface TransactionHistoryService {
	List<TransactionHistory> getAllTransactionHistory();
	TransactionHistory getTransactionHistoryByTransactionHistoryId(int transactionHistoryId) throws TransactionHistoryNotFoundException;
	List<TransactionHistory> getTransactionHistoryByUserId(int userId) throws UserNotFoundException;
	List<TransactionHistory> getTransactionHistoryByTransactionStatus(TransactionStatus transactionStatus);
	List<TransactionHistory> getTransactionHistoryByTransactionType(TxnHistoryTransactionType transactionType);
	SuccessResponse createTransaction(TransactionHistoryDTO transactionHistoryDto) throws TransactionTypeNotFoundException, UserNotFoundException, VendorBranchNotFoundException;
}
