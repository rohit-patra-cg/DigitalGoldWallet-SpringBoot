package com.example.demo.exception;

import java.util.Arrays;
import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import com.example.demo.dto.ErrorDetails;
import com.example.demo.dto.ValidationErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(UserNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleCustomerNotFoundException(UserNotFoundException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(new Date(), ex.getMessage()));
	}
	
	@ExceptionHandler(UserAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(UserAlreadyExistsException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(new Date(), ex.getMessage()));
	}
	
	@ExceptionHandler(AddressNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleAddressNotFoundException(AddressNotFoundException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(new Date(), ex.getMessage()));
	}
	
	@ExceptionHandler(TransactionHistoryNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleTransactionHistoryNotFoundException(TransactionHistoryNotFoundException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(new Date(), ex.getMessage()));
	}

	@ExceptionHandler(InvalidBalanceException.class)
	public ResponseEntity<ValidationErrorDetails> handleInvalidBalanceException(InvalidBalanceException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorDetails(new Date(), Arrays.asList(ex.getMessage())));
	}
	
	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorDetails> handleValidationException(MethodArgumentNotValidException ex, WebRequest request) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorDetails(new Date(), ex.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList())));
	}
}
