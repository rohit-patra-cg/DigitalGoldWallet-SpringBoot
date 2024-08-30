package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.User;
import com.example.demo.entity.VirtualGoldHolding;
import com.example.demo.exception.UserNotFoundException;

public interface UserService {
	List<User> getAllUsers();
	User getUserByUserId(int userId) throws UserNotFoundException;
	User getUserByUsername(String name) throws UserNotFoundException;
	List<User> getAllUsersByCity(String city);
	List<User> getAllUsersByState(String state);
	Double getUserBalanceByUserId(int userId) throws UserNotFoundException;
	List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserId(int userId) throws UserNotFoundException;
}
