package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.LoginBody;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Payment;
import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.User;
import com.example.demo.entity.VirtualGoldHolding;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;

public interface UserService {
	List<User> getAllUsers();
	User getUserByUserId(int userId) throws UserNotFoundException;
	User getUserByUsername(String name) throws UserNotFoundException;
	List<User> getAllUsersByCity(String city);
	List<User> getAllUsersByState(String state);
	Double getUserBalanceByUserId(int userId) throws UserNotFoundException;
	List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserId(int userId) throws UserNotFoundException;
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByUserId(int userId) throws UserNotFoundException;
	List<TransactionHistory> getAllTransactionsByUserId(int userId) throws UserNotFoundException;
	List<Payment> getAllPaymentsByUserId(int userId) throws UserNotFoundException;
	SuccessResponse createUser(UserDTO userDto) throws UserAlreadyExistsException, AddressNotFoundException;
	SuccessResponse updateUser(int userId, UserDTO user) throws UserNotFoundException, AddressNotFoundException;
	SuccessResponse updateUserBalance(int userId, double newBalance) throws UserNotFoundException;
	SuccessResponse updateUserAddress(int userId, int addressId) throws UserNotFoundException, AddressNotFoundException;
	
	//For Authentication returns jwt
	String loginUser(LoginBody loginBody);
}
