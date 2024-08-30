package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TransactionHistory;
import com.example.demo.exception.TransactionHistoryNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.TransactionHistoryRepository;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<TransactionHistory> getAllTransactionHistory() {
		return transactionHistoryRepository.findAll();
	}

	@Override
	public TransactionHistory getTransactionHistoryByTransactionHistoryId(int transactionHistoryId)
			throws TransactionHistoryNotFoundException {
		return transactionHistoryRepository.findById(transactionHistoryId).orElseThrow(
				() -> new TransactionHistoryNotFoundException("Transaction History with Id " + transactionHistoryId + " not found."));
	}

	@Override
	public List<TransactionHistory> getTransactionHistoryByUserId(int userId)
			throws UserNotFoundException {
		userService.getUserByUserId(userId);
		return transactionHistoryRepository.findAllByUserId(userId);
	}
}
