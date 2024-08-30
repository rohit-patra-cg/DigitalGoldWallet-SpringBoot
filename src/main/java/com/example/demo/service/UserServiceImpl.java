package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.UserRepository;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	public UserServiceImpl(UserRepository userRepository) {
		this.userRepository = userRepository;
	}

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

}
