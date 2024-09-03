package com.example.demo.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.access.intercept.AuthorizationFilter;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
public class WebSecurityConfig {

	private JWTRequestFilter jwtRequestFilter;

	public WebSecurityConfig(JWTRequestFilter jwtRequestFilter) {
		this.jwtRequestFilter = jwtRequestFilter;
	}

	@Bean
	SecurityFilterChain filterChain(HttpSecurity httpSec) throws Exception {
		httpSec.csrf(AbstractHttpConfigurer::disable).cors(AbstractHttpConfigurer::disable)
				.cors(sec -> sec.configurationSource(corsConfigurationSource()))
				.formLogin(AbstractHttpConfigurer::disable).addFilterBefore(jwtRequestFilter, AuthorizationFilter.class)
				.authorizeHttpRequests(auth -> auth.requestMatchers("/api/auth/login", "/api/v1/users/add", "/api/v1/address/add").permitAll()
						.anyRequest().authenticated());
		return httpSec.build();
	}

	CorsConfigurationSource corsConfigurationSource() {
		CorsConfiguration configuration = new CorsConfiguration();
		configuration.setAllowCredentials(true);
		configuration.addAllowedOrigin("http://localhost:4200");
		configuration.addAllowedHeader("*");
		configuration.addAllowedMethod("*");
		UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
		source.registerCorsConfiguration("/**", configuration);
		return source;
	}
}