package com.example.demo.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;

public record LoginBody(
        @NotBlank(message = "Username is required")
        @Size(max = 255, min = 3)
        String username,
        
        /**
         * Minimum eight characters, at least one upper-case letter, at least one lower-case letter, one number and at least one special character
         */
        @NotBlank(message = "Password is required")
        @Pattern(regexp = "(?=.*\\d)(?=.*[@$#!%*?&])[A-Za-z\\d@$!#%*?&]{8,}$", message = "Password is invalid")
        @Size(max = 32, min = 8, message="Password size must be between 8 and 32")
        String password
) {}