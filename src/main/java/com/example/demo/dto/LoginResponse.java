package com.example.demo.dto;

public record LoginResponse(String jwt, Boolean success, String failureMessage, Integer userId) {
}
