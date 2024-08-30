package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.PostResponse;
import com.example.demo.entity.Payment;
import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.User;
import com.example.demo.entity.VirtualGoldHolding;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.PhysicalGoldTransactionRepository;
import com.example.demo.repository.TransactionHistoryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VirtualGoldHoldingRepository;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private VirtualGoldHoldingRepository virtualGoldHoldingRepository;

	@Autowired
	private PhysicalGoldTransactionRepository physicalGoldTransactionRepository;
	
	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;
	
	@Autowired
	private PaymentRepository paymentRepository;

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByUserId(int userId) throws UserNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User#" + userId + " not found."));
	}

	@Override
	public User getUserByUsername(String name) throws UserNotFoundException {
		return userRepository.findByName(name)
				.orElseThrow(() -> new UserNotFoundException("User with " + name + " not found."));
	}

	@Override
	public List<User> getAllUsersByCity(String city) {
		return userRepository.findAllByCity(city);
	}

	@Override
	public List<User> getAllUsersByState(String state) {
		return userRepository.findAllByState(state);
	}

	@Override
	public Double getUserBalanceByUserId(int userId) throws UserNotFoundException {
		User user = getUserByUserId(userId);
		return user.getBalance();
	}

	@Override
	public List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserId(int userId) throws UserNotFoundException {
		getUserByUserId(userId);
		return virtualGoldHoldingRepository.findAllVirtualGoldHoldingByUserId(userId);
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByUserId(int userId)
			throws UserNotFoundException {
		getUserByUserId(userId);
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByUserId(userId);
	}

	@Override
	public List<TransactionHistory> getAllTransactionsByUserId(int userId) throws UserNotFoundException {
		getUserByUserId(userId);
		return transactionHistoryRepository.findAllByUserId(userId);
	}

	@Override
	public List<Payment> getAllPaymentsByUserId(int userId) throws UserNotFoundException {
		getUserByUserId(userId);
		return paymentRepository.findAllByUserId(userId);
	}

	@Override
	public PostResponse createUser(User user) throws UserAlreadyExistsException {
		if (userRepository.findByName(user.getName()).isEmpty()) {
			userRepository.save(user);
			return new PostResponse(new Date(), "User details added successfully.");
		}
		throw new UserAlreadyExistsException("User already exists.");
	}

}
