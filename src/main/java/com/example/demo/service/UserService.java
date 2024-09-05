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
	
     /**
	 * Get All Users
	 * @return List<User> Collection of Users
	 */
	List<User> getAllUsers();
	
	/**
	 * Get User by user_id
	 * @param userId
	 * @return User Object
	 * @throws UserNotFoundException
	 */
	User getUserByUserId(int userId) throws UserNotFoundException;
	
	/**
	 * Get User by user_name
	 * @param name
	 * @return User Object
	 * @throws UserNotFoundException
	 */
	User getUserByUsername(String name) throws UserNotFoundException;
	
	/**
	 * Get All User by city
	 * @param city
	 * @return List<User> Collection of Users 
	 */
	List<User> getAllUsersByCity(String city);
	
	/**
	 * Get All User by state
	 * @param state
	 * @return List<User> Collection of Users 
	 */
	List<User> getAllUsersByState(String state);
	
	/**
	 * Get User Balance by user_id
	 * @param userId
	 * @return Double User's current balance
	 * @throws UserNotFoundException
	 */
	Double getUserBalanceByUserId(int userId) throws UserNotFoundException;
	
	/**
	 * Get Total virtual gold holding of a User
	 * @param userId
	 * @return List<VirtualGoldHolding> Total virtual gold a user is holding currently 
	 * @throws UserNotFoundException
	 */
	List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserId(int userId) throws UserNotFoundException;
	
	/**
	 * Get Total physical gold a User owns
	 * @param userId
	 * @return List<PhysicalGoldTransaction> Total physical gold a user owns
	 * @throws UserNotFoundException
	 */
	List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByUserId(int userId) throws UserNotFoundException;
	
	/**
	 * Get User transaction history
	 * @param userId
	 * @return List<TransactionHistory> Collection of TransactionHistory
	 * @throws UserNotFoundException
	 */
	List<TransactionHistory> getAllTransactionsByUserId(int userId) throws UserNotFoundException;
	
	/**
	 * Get User payments  
	 * @param userId
	 * @return List<Payment> Collection of Payments
	 * @throws UserNotFoundException
	 */
	List<Payment> getAllPaymentsByUserId(int userId) throws UserNotFoundException;
	
	/**
	 * Add new User
	 * @param userDto
	 * @return SuccessResponse Response for successfully adding a user(Sign up)
	 * @throws UserAlreadyExistsException
	 * @throws AddressNotFoundException
	 */
	SuccessResponse createUser(UserDTO userDto) throws UserAlreadyExistsException, AddressNotFoundException;
	
	/**
	 * Update User data
	 * @param userId,userDto
	 * @return SuccessResponse Response for successfully updating a user's data
	 * @throws UserNotFoundException
	 * @throws AddressNotFoundException
	 */
	SuccessResponse updateUser(int userId, UserDTO user) throws UserNotFoundException, AddressNotFoundException;
	
	/**
	 * Update User balance
	 * @param userId, newBalance
	 * @return SuccessResponse Response for successfully updating a user's balance
	 * @throws UserNotFoundException
	 */
	SuccessResponse updateUserBalance(int userId, double newBalance) throws UserNotFoundException;
	
	/**
	 * Update User address
	 * @param userId, addressId
	 * @return SuccessResponse Response for successfully updating a user's address
	 * @throws UserNotFoundException
	 * @throws AddressNotFoundException
	 */
	SuccessResponse updateUserAddress(int userId, int addressId) throws UserNotFoundException, AddressNotFoundException;
	
	/**
	 * Secure user login
	 * @param loginBody DTO
	 * @return jwtToken Creates and Returns a JWT token after successful login 
	 */
	//For Authentication returns jwt
	String loginUser(LoginBody loginBody);
}
