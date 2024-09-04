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
	
	/**
	 * Get All Transaction History
	 * @return List<TransactionHistory> Collection of TransactionHistory
	 */
	@Override
	public List<TransactionHistory> getAllTransactionHistory() {
		return transactionHistoryRepository.findAll();
	}
	
	/**
	 * Get Transaction History by transaction_id
	 * @param transactionHistoryId
	 * @return TransactionHistory Object
	 * @throws TransactionHistoryNotFoundException
	 */
	@Override
	public TransactionHistory getTransactionHistoryByTransactionHistoryId(int transactionHistoryId)
			throws TransactionHistoryNotFoundException {
		return transactionHistoryRepository.findById(transactionHistoryId).orElseThrow(
				() -> new TransactionHistoryNotFoundException("Transaction History with Id " + transactionHistoryId + " not found."));
	}
	
	/**
	 * Get All Transaction History by user_id
	 * @param userId
	 * @return List<TransactionHistory> Collection of TransactionHistory
	 * @throws UserNotFoundException
	 */
	@Override
	public List<TransactionHistory> getTransactionHistoryByUserId(int userId)
			throws UserNotFoundException {
		userService.getUserByUserId(userId);
		return transactionHistoryRepository.findAllByUserId(userId);
	}
	
	/**
	 * Get All Transaction History by TransactionStatus 
	 * @param transactionStatus
	 * @return List<TransactionHistory> Collection of TransactionHistory
	 */
	@Override
	public List<TransactionHistory> getTransactionHistoryByTransactionStatus(TransactionStatus transactionStatus) {
		return transactionHistoryRepository.findAllByTransactionStatus(transactionStatus);
	}

	/**
	 * Get All Transaction History by TransactionType
	 * @param transactionType
	 * @return List<TransactionHistory> Collection of TransactionHistory
	 */
	@Override
	public List<TransactionHistory> getTransactionHistoryByTransactionType(TxnHistoryTransactionType transactionType) {
		return transactionHistoryRepository.findAllByTransactionType(transactionType);
	}
	
	/**
	 * Add New Transaction History
	 * @param transactionHistoryDto
	 * @return SuccessResponse Response for successfully adding transaction history
	 * @throws UserNotFoundException, VendorBranchNotFoundException
	 */
	@Override
	public SuccessResponse createTransaction(TransactionHistoryDTO transactionHistoryDto)
			throws UserNotFoundException, VendorBranchNotFoundException {
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
		return new SuccessResponse(new Date(), "transaction was succesful", transactionHistory.getTransactionId());
	}
}
