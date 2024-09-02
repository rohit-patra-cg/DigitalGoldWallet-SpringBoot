package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Address;
import com.example.demo.entity.User;
import com.example.demo.entity.VirtualGoldHolding;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.PhysicalGoldTransactionRepository;
import com.example.demo.repository.TransactionHistoryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VirtualGoldHoldingRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

	@Mock
	private UserRepository userRepository;

	@Mock
	private AddressService addressService;

	@Mock
	private VirtualGoldHoldingRepository virtualGoldHoldingRepository;

	@Mock
	private PhysicalGoldTransactionRepository physicalGoldTransactionRepository;

	@Mock
	private TransactionHistoryRepository transactionHistoryRepository;

	@Mock
	private PaymentRepository paymentRepository;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Test
	void testGetAllUsers() {
		User user1 = new User(1, "john@example.com", "John Doe", new Address(), 1000.0, LocalDateTime.now());
		User user2 = new User(2, "jane@example.com", "Jane Doe", new Address(), 1000.0, LocalDateTime.now());
		List<User> users = Arrays.asList(user1, user2);

		when(userRepository.findAll()).thenReturn(users);

		List<User> result = userServiceImpl.getAllUsers();
		assertEquals(2, result.size());
		assertEquals("John Doe", result.get(0).getName());
		assertEquals("Jane Doe", result.get(1).getName());
	}

	@Test
	void testGetUserByUserId_UserExists() throws UserNotFoundException {
		User user = new User(1, "john@example.com", "John Doe", null, 100.0, LocalDateTime.now());
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		User result = userServiceImpl.getUserByUserId(1);
		assertEquals(user, result);
	}

	@Test
	void testGetUserByUserId_UserNotFound() {
		when(userRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserByUserId(1));
	}

	@Test
	void testGetUserBalanceByUserId_UserExists() throws UserNotFoundException {
		User user = new User(1, "john@example.com", "John Doe", null, 100.0, LocalDateTime.now());
		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		Double balance = userServiceImpl.getUserBalanceByUserId(1);
		assertEquals(100.0, balance);
	}

	@Test
	void testGetUserBalanceByUserId_UserNotFound() {
		when(userRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userServiceImpl.getUserBalanceByUserId(1));
	}

	@Test
	void testGetAllVirtualGoldHoldingsByUserId_WhenUserExists() throws UserNotFoundException {
		User user = new User();
		user.setUserId(1);
		user.setEmail("test@example.com");
		user.setName("Test User");

		VirtualGoldHolding holding1 = new VirtualGoldHolding();
		holding1.setHoldingId(1);
		holding1.setUser(user);
		holding1.setQuantity(10.0);

		VirtualGoldHolding holding2 = new VirtualGoldHolding();
		holding2.setHoldingId(2);
		holding2.setUser(user);
		holding2.setQuantity(20.0);

		when(userRepository.findById(1)).thenReturn(Optional.of(user));
		when(virtualGoldHoldingRepository.findAllVirtualGoldHoldingByUserId(1))
				.thenReturn(Arrays.asList(holding1, holding2));

		List<VirtualGoldHolding> holdings = userServiceImpl.getAllVirtualGoldHoldingsByUserId(1);

		assertEquals(2, holdings.size());
		assertEquals(holding1, holdings.get(0));
		assertEquals(holding2, holdings.get(1));
	}

	@Test
	void testGetAllVirtualGoldHoldingsByUserId_UserNotFound() {
		when(userRepository.findById(1)).thenReturn(Optional.empty());
		assertThrows(UserNotFoundException.class, () -> userServiceImpl.getAllVirtualGoldHoldingsByUserId(1));
	}
}
