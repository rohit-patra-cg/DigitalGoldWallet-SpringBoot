package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TransactionHistory;
import com.example.demo.exception.TransactionHistoryNotFoundException;
import com.example.demo.exception.UserNotFoundException;

public interface TransactionHistoryService {
	
	List<TransactionHistory> getAllTransactionHistory();
	TransactionHistory getTransactionHistoryByTransactionHistoryId(int TransactionHistoryId) throws TransactionHistoryNotFoundException;
	List<TransactionHistory> getTransactionHistoryByUserId(int userId) throws UserNotFoundException;
}
