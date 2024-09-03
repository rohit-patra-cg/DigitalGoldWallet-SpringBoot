package com.example.demo.service;

import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.example.demo.dto.LoginBody;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.UserDTO;
import com.example.demo.entity.Address;
import com.example.demo.entity.Payment;
import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.User;
import com.example.demo.entity.VirtualGoldHolding;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.exception.UserAlreadyExistsException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.repository.PaymentRepository;
import com.example.demo.repository.PhysicalGoldTransactionRepository;
import com.example.demo.repository.TransactionHistoryRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.repository.VirtualGoldHoldingRepository;
import com.example.demo.security.EncryptionService;
import com.example.demo.security.JWTService;

@Service
public class UserServiceImpl implements UserService {

	private UserRepository userRepository;

	private AddressService addressService;

	private VirtualGoldHoldingRepository virtualGoldHoldingRepository;

	private PhysicalGoldTransactionRepository physicalGoldTransactionRepository;

	private TransactionHistoryRepository transactionHistoryRepository;

	private PaymentRepository paymentRepository;
	
	private EncryptionService encryptionService;
	
	private JWTService jwtService;

	

	public UserServiceImpl(UserRepository userRepository, AddressService addressService,
			VirtualGoldHoldingRepository virtualGoldHoldingRepository,
			PhysicalGoldTransactionRepository physicalGoldTransactionRepository,
			TransactionHistoryRepository transactionHistoryRepository, PaymentRepository paymentRepository,
			EncryptionService encryptionService, JWTService jwtService) {
		this.userRepository = userRepository;
		this.addressService = addressService;
		this.virtualGoldHoldingRepository = virtualGoldHoldingRepository;
		this.physicalGoldTransactionRepository = physicalGoldTransactionRepository;
		this.transactionHistoryRepository = transactionHistoryRepository;
		this.paymentRepository = paymentRepository;
		this.encryptionService = encryptionService;
		this.jwtService = jwtService;
	}

	@Override
	public List<User> getAllUsers() {
		return userRepository.findAll();
	}

	@Override
	public User getUserByUserId(int userId) throws UserNotFoundException {
		return userRepository.findById(userId)
				.orElseThrow(() -> new UserNotFoundException("User#" + userId + " not found."));
	}

	@Override
	public User getUserByUsername(String name) throws UserNotFoundException {
		return userRepository.findByName(name)
				.orElseThrow(() -> new UserNotFoundException("User with " + name + " not found."));
	}

	@Override
	public List<User> getAllUsersByCity(String city) {
		return userRepository.findAllByCity(city);
	}

	@Override
	public List<User> getAllUsersByState(String state) {
		return userRepository.findAllByState(state);
	}

	@Override
	public Double getUserBalanceByUserId(int userId) throws UserNotFoundException {
		User user = getUserByUserId(userId);
		return user.getBalance();
	}

	@Override
	public List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserId(int userId) throws UserNotFoundException {
		getUserByUserId(userId);
		return virtualGoldHoldingRepository.findAllVirtualGoldHoldingByUserId(userId);
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByUserId(int userId)
			throws UserNotFoundException {
		getUserByUserId(userId);
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByUserId(userId);
	}

	@Override
	public List<TransactionHistory> getAllTransactionsByUserId(int userId) throws UserNotFoundException {
		getUserByUserId(userId);
		return transactionHistoryRepository.findAllByUserId(userId);
	}

	@Override
	public List<Payment> getAllPaymentsByUserId(int userId) throws UserNotFoundException {
		getUserByUserId(userId);
		return paymentRepository.findAllByUserId(userId);
	}

	@Override
	public SuccessResponse createUser(UserDTO userDto) throws UserAlreadyExistsException, AddressNotFoundException {
		if (userRepository.findByEmail(userDto.getEmail()).isEmpty()) {
			Address address = addressService.getAddressByAddressId(userDto.getAddressId());
			User user = new User();
			user.setName(userDto.getName());
			user.setEmail(userDto.getEmail());
			user.setBalance(userDto.getBalance());
			user.setAddress(address);
			user.setPasswordHash(encryptionService.encryptPassword(userDto.getPassword()));
			User savedUser = userRepository.save(user);
			return new SuccessResponse(new Date(), "User details added successfully", savedUser.getUserId());
		}
		throw new UserAlreadyExistsException("User already exists");
	}

	@Override
	public SuccessResponse updateUser(int userId, UserDTO userDto)
			throws UserNotFoundException, AddressNotFoundException {
		Address address = addressService.getAddressByAddressId(userDto.getAddressId());
		User user = getUserByUserId(userId);
		user.setName(userDto.getName());
		user.setEmail(userDto.getEmail());
		user.setBalance(userDto.getBalance());
		user.setAddress(address);
		userRepository.save(user);
		return new SuccessResponse(new Date(), "User details updated successfully", userId);
	}

	@Override
	public SuccessResponse updateUserBalance(int userId, double newBalance) throws UserNotFoundException {
		User user = getUserByUserId(userId);
		user.setBalance(newBalance);
		userRepository.save(user);
		return new SuccessResponse(new Date(), "User balance updated successfully", userId);
	}

	@Override
	public SuccessResponse updateUserAddress(int userId, int addressId)
			throws UserNotFoundException, AddressNotFoundException {
		User user = getUserByUserId(userId);
		Address address = addressService.getAddressByAddressId(addressId);
		user.setAddress(address);
		userRepository.save(user);
		return new SuccessResponse(new Date(), "User address updated successfully", userId);
	}

	@Override
	public String loginUser(LoginBody loginBody) {
		Optional<User> optUser = userRepository.findByEmailIgnoreCase(loginBody.username());
		if (optUser.isPresent()) {
			User user = optUser.get();
			if (encryptionService.verifyPassword(loginBody.password(), user.getPasswordHash())) {
				return jwtService.generateJWT(user);
			}
		}
		return null;
	}
}
