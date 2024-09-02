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

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactions() {
		return physicalGoldTransactionRepository.findAll();
	}

	@Override
	public PhysicalGoldTransaction getPhysicalGoldTransactionById(int physicalGoldTransactionId) throws PhysicalGoldTransactionNotFoundException {
		return physicalGoldTransactionRepository.findById(physicalGoldTransactionId).orElseThrow(() -> new PhysicalGoldTransactionNotFoundException("PhysicalGoldTransaction#" + physicalGoldTransactionId + " not found"));
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByUserId(int userId) throws UserNotFoundException {
		userService.getUserByUserId(userId);
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByUserId(userId);
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByBranchId(int branchId) throws VendorBranchNotFoundException {
		vendorBranchService.getVendorBranchByBranchId(branchId);
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByBranchId(branchId);
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryCity(String city) {
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByCity(city);
	}

	@Override
	public List<PhysicalGoldTransaction> getAllPhysicalGoldTransactionsByDeliveryState(String state) {
		return physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByState(state);
	}

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
		physicalGoldTransactionRepository.save(physicalGoldTransaction);
		return new SuccessResponse(new Date(), "Physical Gold Transaction added successfully");
	}

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
		return new SuccessResponse(new Date(), "Physical Gold Transaction updated successfully");
	}
}
