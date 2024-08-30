package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;

@RestController
@RequestMapping("/api/v1/users")
public class UserController {

	@Autowired
	private UserService userService;

	@GetMapping
	ResponseEntity<List<User>> getAllUsers() {
		return ResponseEntity.ok(userService.getAllUsers());
	}

	@GetMapping("/{user_id}")
	ResponseEntity<User> getUserByUserId(@PathVariable("user_id") int userId) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getUserByUserId(userId));
	}

	@GetMapping("/name/{user_name}")
	ResponseEntity<User> getUserByUserName(@PathVariable("user_name") String username) throws UserNotFoundException {
		return ResponseEntity.ok(userService.getUserByUsername(username));
	}

	@GetMapping("/by_city/{city}")
	ResponseEntity<List<User>> getUserByUserCity(@PathVariable("city") String city) {
		return ResponseEntity.ok(userService.getAllUsersByCity(city));
	}

	@GetMapping("/by_state/{state}")
	ResponseEntity<List<User>> getUserByUserState(@PathVariable("state") String state) {
		return ResponseEntity.ok(userService.getAllUsersByState(state));
	}

}
