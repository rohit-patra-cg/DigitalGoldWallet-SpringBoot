package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.PhysicalGoldTransactionDTO;
import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Address;
import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.entity.User;
import com.example.demo.entity.VendorBranch;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.exception.PhysicalGoldTransactionNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.repository.PhysicalGoldTransactionRepository;

@Service
public class PhysicalGoldTransactionServiceImpl implements PhysicalGoldTransactionService {

	private PhysicalGoldTransactionRepository physicalGoldTransactionRepository;
	
	private UserService userService;

	private AddressService addressService;
	
	private VendorBranchService vendorBranchService;
	
	public PhysicalGoldTransactionServiceImpl(PhysicalGoldTransactionRepository physicalGoldTransactionRepository,
			UserService userService, AddressService addressService, VendorBranchService vendorBranchService) {
		this.physicalGoldTransactionRepository = physicalGoldTransactionRepository;
		this.userService = userService;
		this.addressService = addressService;
		this.vendorBranchService = vendorBranchService;
	}
	
	/**
	 * Get All Physical Gold Transactions
	 * @return List<PhysicalGoldTransaction> Collection of PhysicalGoldTransactions
	 */
	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactions() {
		return physicalGoldTransactionRepository.findAll();
	}
	
	/**
	 * Get Physical Gold Transactions by transaction_id
	 * @param physicalGoldTransactionId
	 * @return PhysicalGoldTransaction Object
	 * @throws PhysicalGoldTransactionNotFoundException
	 */
	@Override
	public PhysicalGoldTransaction getPhysicalGoldTransactionById(int physicalGoldTransactionId) throws PhysicalGoldTransactionNotFoundException {
		return physicalGoldTransactionRepository.findById(physicalGoldTransactionId).orElseThrow(() -> new PhysicalGoldTransactionNotFoundException("PhysicalGoldTransaction#" + physicalGoldTransactionId + " not found"));
	}
	
	/**
	 * Get Physical Gold Transactions by user_id
	 * @param userId
	 * @return List<PhysicalGoldTransaction> Collection of PhysicalGoldTransactions
	 * @throws UserNotFoundException
	 */
	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByUserId(int userId) throws UserNotFoundException {
		userService.getUserByUserId(userId);
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByUserId(userId);
	}
	
	/**
	 * Get Physical Gold Transactions by branch_id
	 * @param branchId
	 * @return List<PhysicalGoldTransaction> Collection of PhysicalGoldTransactions
	 * @throws VendorBranchNotFoundException
	 */
	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByBranchId(int branchId) throws VendorBranchNotFoundException {
		vendorBranchService.getVendorBranchByBranchId(branchId);
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByBranchId(branchId);
	}
	
	/**
	 * Get All Physical Gold Transactions by delivery city 
	 * @param city
	 * @return List<PhysicalGoldTransaction> Collection of PhysicalGoldTransactions 
	 */
	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryCity(String city) {
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByCity(city);
	}
	
	/**
	 * Get All Physical Gold Transactions by delivery state
	 * @param state
	 * @return List<PhysicalGoldTransaction> Collection of PhysicalGoldTransactions
	 */
	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryState(String state) {
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByState(state);
	}
	
	/**
	 * Add New Physical Gold Transactions
	 * @param dto
	 * @return SuccessResponse Response for successfully adding new physical gold transaction
	 * @throws UserNotFoundException
	 * @throws AddressNotFoundException 
	 * @throws VendorBranchNotFoundException
	 */
	@Override
	public SuccessResponse createPhysicalGoldTransaction(PhysicalGoldTransactionDTO dto) throws UserNotFoundException, AddressNotFoundException, VendorBranchNotFoundException {
		User user = userService.getUserByUserId(dto.getUserId());
		Address address = addressService.getAddressByAddressId(dto.getAddressId());
		VendorBranch branch = vendorBranchService.getVendorBranchByBranchId(dto.getBranchId());
		PhysicalGoldTransaction physicalGoldTransaction = new PhysicalGoldTransaction();
		physicalGoldTransaction.setQuantity(dto.getQuantity());
		physicalGoldTransaction.setUser(user);
		physicalGoldTransaction.setDeliveryAddress(address);
		physicalGoldTransaction.setBranch(branch);
		PhysicalGoldTransaction savedTransaction = physicalGoldTransactionRepository.save(physicalGoldTransaction);
		return new SuccessResponse(new Date(), "Physical Gold Transaction added successfully", savedTransaction.getTransactionId());
	}

	/**
	 * Update Physical Gold Transactions by transaction_id
	 * @param transactionId, PhysicalGoldTransactionDTO
	 * @return SuccessResponse Response for successfully updating new physical gold transaction
	 * @throws PhysicalGoldTransactionNotFoundException
	 * @throws VendorBranchNotFoundException
	 * @throws UserNotFoundException 
	 * @throws AddressNotFoundException
	 */
	@Override
	public SuccessResponse updatePhysicalGoldTransaction(int transactionId, PhysicalGoldTransactionDTO dto) throws PhysicalGoldTransactionNotFoundException, VendorBranchNotFoundException, UserNotFoundException, AddressNotFoundException {
		User user = userService.getUserByUserId(dto.getUserId());
		Address address = addressService.getAddressByAddressId(dto.getAddressId());
		VendorBranch branch = vendorBranchService.getVendorBranchByBranchId(dto.getBranchId());
		PhysicalGoldTransaction physicalGoldTransaction = getPhysicalGoldTransactionById(transactionId);
		physicalGoldTransaction.setQuantity(dto.getQuantity());
		physicalGoldTransaction.setUser(user);
		physicalGoldTransaction.setDeliveryAddress(address);
		physicalGoldTransaction.setBranch(branch);
		physicalGoldTransactionRepository.save(physicalGoldTransaction);
		return new SuccessResponse(new Date(), "Physical Gold Transaction updated successfully", transactionId);
	}
}
