package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.exception.PhysicalGoldTransactionNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PhysicalGoldTransactionRepository;

@Service
public class PhysicalGoldTransactionServiceImpl implements PhysicalGoldTransactionService {

	@Autowired
	private PhysicalGoldTransactionRepository physicalGoldTransactionRepository;
	
	@Autowired
	private UserService userService;

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactions() {
		return physicalGoldTransactionRepository.findAll();
	}

	@Override
	public PhysicalGoldTransaction getPhysicalGoldTransactionById(int physicalGoldTransactionId) throws PhysicalGoldTransactionNotFoundException {
		return physicalGoldTransactionRepository.findById(physicalGoldTransactionId).orElseThrow(() -> new PhysicalGoldTransactionNotFoundException("PhysicalGoldTransaction#" + physicalGoldTransactionId + " not found"));
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByUserId(int userId) throws UserNotFoundException {
		userService.getUserByUserId(userId);
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByUserId(userId);
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByBranchId(int branchId) {
		// TODO Add check for branch exists or not..
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByBranchId(branchId);
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryCity(String city) {
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByCity(city);
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryState(String state) {
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByState(state);
	}
}
