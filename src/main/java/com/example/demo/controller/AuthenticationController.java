package com.example.demo.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.LoginBody;
import com.example.demo.dto.LoginResponse;
import com.example.demo.entity.User;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.service.UserService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/auth")
public class AuthenticationController {

	private UserService userService;

	public AuthenticationController(UserService userService) {
		this.userService = userService;
	}
    
	/**
	 * Handles HTTP POST requests to authenticate a user and provide a JWT upon successful login.
	 * @param loginBody
	 * @return ResponseEntity containing a LoginResponse object and an HTTP status code(200)
	 * @throws UserNotFoundException 
	 */
	@PostMapping("/login")
	public ResponseEntity<LoginResponse> loginUser(@Valid @RequestBody LoginBody loginBody) throws UserNotFoundException {
		String jwt;
		jwt = userService.loginUser(loginBody);

		if (jwt == null) {
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).build();
		}
		LoginResponse loginResponse = new LoginResponse(jwt, true, null, userService.getUserByEmail(loginBody.username()).getUserId());
		return ResponseEntity.ok(loginResponse);
	}
	
	/**
	 * Handles HTTP GET requests to retrieve the profile information of the currently logged-in user.
	 * @param user
	 * @return User object containing the profile information of the logged-in user
	 */
    @GetMapping("/me")
	public User getLoggedInUserProfile(@AuthenticationPrincipal User user) {
		return user;
	}

}