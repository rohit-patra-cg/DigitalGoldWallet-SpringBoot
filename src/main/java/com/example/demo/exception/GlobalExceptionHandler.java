package com.example.demo.exception;

import java.util.Date;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.example.demo.dto.ErrorDetails;
import com.example.demo.dto.ValidationErrorDetails;

@RestControllerAdvice
public class GlobalExceptionHandler {

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<ValidationErrorDetails> handleValidationException(MethodArgumentNotValidException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ValidationErrorDetails(new Date(), ex.getFieldErrors().stream().map(e -> e.getDefaultMessage()).collect(Collectors.toList())));
	}
	
	@ExceptionHandler(ResourceNotFoundException.class)
	public ResponseEntity<ErrorDetails> handleResourceNotFoundException(ResourceNotFoundException ex) {
		return ResponseEntity.status(HttpStatus.NOT_FOUND).body(new ErrorDetails(new Date(), ex.getMessage()));
	}
	
	@ExceptionHandler(ResourceAlreadyExistsException.class)
	public ResponseEntity<ErrorDetails> handleUserAlreadyExistsException(ResourceAlreadyExistsException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(new Date(), ex.getMessage()));
	}

	@ExceptionHandler(InvalidBalanceException.class)
	public ResponseEntity<ErrorDetails> handleInvalidBalanceException(InvalidBalanceException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(new Date(), ex.getMessage()));
	}
	
	@ExceptionHandler(InvalidGoldPriceException.class)
	public ResponseEntity<ErrorDetails> handleInvalidGoldPriceException(InvalidGoldPriceException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(new Date(), ex.getMessage()));
	}
	
	@ExceptionHandler(InvalidGoldQuantityException.class)
	public ResponseEntity<ErrorDetails> handleInvalidGoldQuantityException(InvalidGoldQuantityException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(new Date(), ex.getMessage()));
	}
	
	@ExceptionHandler(InvalidAmountException.class)
	public ResponseEntity<ErrorDetails> handleInvalidAmountException(InvalidAmountException ex) {
		return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(new ErrorDetails(new Date(), ex.getMessage()));
	}
}
