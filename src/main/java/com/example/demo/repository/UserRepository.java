package com.example.demo.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.example.demo.entity.User;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
	Optional<User> findByName(String name);
	
	@Query("FROM User u WHERE u.address.city=:city")
	List<User> findAllByCity(String city);
	
	@Query("FROM User u WHERE u.address.state=:state")
	List<User> findAllByState(String state);
	
}
