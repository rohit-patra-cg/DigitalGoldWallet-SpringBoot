package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.TransactionHistoryDTO;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.User;
import com.example.demo.entity.VendorBranch;
import com.example.demo.enums.TransactionStatus;
import com.example.demo.enums.TxnHistoryTransactionType;
import com.example.demo.exception.TransactionHistoryNotFoundException;
import com.example.demo.exception.TransactionTypeNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.repository.TransactionHistoryRepository;

@Service
public class TransactionHistoryServiceImpl implements TransactionHistoryService {

    private TransactionHistoryRepository transactionHistoryRepository;
    
	private UserService userService;
	
	private VendorBranchService vendorBranchService;
	
	public TransactionHistoryServiceImpl(TransactionHistoryRepository transactionHistoryRepository,
		UserService userService, VendorBranchService vendorBranchService) {
		this.transactionHistoryRepository = transactionHistoryRepository;
		this.userService = userService;
		this.vendorBranchService = vendorBranchService;
	}

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

	@Override
	public List<TransactionHistory> getTransactionHistoryByTransactionStatus(TransactionStatus transactionStatus) {
		return transactionHistoryRepository.findAllByTransactionStatus(transactionStatus);
	}

	@Override
	public List<TransactionHistory> getTransactionHistoryByTransactionType(TxnHistoryTransactionType transactionType) {
		return transactionHistoryRepository.findAllByTransactionType(transactionType);
	}
	
	@Override
	public SuccessResponse createTransaction(TransactionHistoryDTO transactionHistoryDto)
			throws TransactionTypeNotFoundException, UserNotFoundException, VendorBranchNotFoundException {
		User user = userService.getUserByUserId(transactionHistoryDto.getUserId());
		VendorBranch branch = vendorBranchService.getVendorBranchByBranchId(transactionHistoryDto.getBranchId());
		TransactionHistory transactionHistory = new TransactionHistory();
		transactionHistory.setUser(user);
		transactionHistory.setBranch(branch);
		transactionHistory.setTransactionType(transactionHistoryDto.getTransactionType());
		transactionHistory.setTransactionStatus(transactionHistoryDto.getTransactionStatus());
		transactionHistory.setQuantity(transactionHistoryDto.getQuantity());
		transactionHistory.setAmount(transactionHistoryDto.getAmount());
		transactionHistoryRepository.save(transactionHistory);
		return new SuccessResponse(new Date(), "transaction was succesful");
	}
}
