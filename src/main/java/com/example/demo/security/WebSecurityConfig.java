package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;

@Configuration
public class WebSecurityConfig {

	private JWTRequestFilter jwtRequestFilter;

	public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception {
		httpSec.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
				.addFilterBefore(jwtRequestFilter, AuthorizationFilter.class)
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/login", "/api/v1/users/add").permitAll()
						.anyRequest().authenticated());
		return httpSec.build();
	}
}