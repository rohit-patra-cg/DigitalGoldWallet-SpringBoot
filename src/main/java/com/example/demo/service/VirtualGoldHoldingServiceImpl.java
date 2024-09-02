package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.VirtualGoldHoldingDTO;
import com.example.demo.entity.User;
import com.example.demo.entity.VendorBranch;
import com.example.demo.entity.VirtualGoldHolding;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.exception.VirtualGoldHoldingAreadyExistsException;
import com.example.demo.exception.VirtualGoldHoldingNotFoundException;
import com.example.demo.repository.VirtualGoldHoldingRepository;

@Service
public class VirtualGoldHoldingServiceImpl implements VirtualGoldHoldingService {
	
	private VirtualGoldHoldingRepository virtualGoldHoldingRepository;
	
	private UserService userService;

	private VendorService vendorService;
	
	private VendorBranchService branchService;
	
	public VirtualGoldHoldingServiceImpl(VirtualGoldHoldingRepository virtualGoldHoldingRepository,
			UserService userService, VendorService vendorService, VendorBranchService branchService) {
		this.virtualGoldHoldingRepository = virtualGoldHoldingRepository;
		this.userService = userService;
		this.vendorService = vendorService;
		this.branchService = branchService;
	}

	@Override
	public List<VirtualGoldHolding> getAllVirtualGoldHoldings() {
		return virtualGoldHoldingRepository.findAll();
	}

	@Override
	public VirtualGoldHolding getVirtualGoldHoldingById(int holdingId) throws VirtualGoldHoldingNotFoundException {
		return virtualGoldHoldingRepository.findById(holdingId).orElseThrow(() -> new VirtualGoldHoldingNotFoundException("VirtualGoldHolding#" + holdingId + " not found"));
	}

	@Override
	public List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserId(int userId) throws UserNotFoundException {
		userService.getUserByUserId(userId);
		return virtualGoldHoldingRepository.findAllVirtualGoldHoldingByUserId(userId);
	}

	@Override
	public List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserIdAndVendorId(int userId, int vendorId) throws UserNotFoundException, VendorNotFoundException {
		userService.getUserByUserId(userId);
		vendorService.getVendorById(vendorId);
		return virtualGoldHoldingRepository.findAllVirtualGoldHoldingByUserIdAndVendorId(userId, vendorId);
	}

	@Override
	public SuccessResponse addVirtualGoldHolding(VirtualGoldHoldingDTO holdingDto)throws VirtualGoldHoldingAreadyExistsException, UserNotFoundException, VendorBranchNotFoundException {
		User user = userService.getUserByUserId(holdingDto.getUserId());
		VendorBranch branch=branchService.getVendorBranchByBranchId(holdingDto.getBranchId());
		VirtualGoldHolding virtualGoldHolding=new VirtualGoldHolding();
		virtualGoldHolding.setBranch(branch);
		virtualGoldHolding.setUser(user);
		virtualGoldHolding.setQuantity(holdingDto.getQuantity());
		virtualGoldHoldingRepository.save(virtualGoldHolding);
		return new SuccessResponse(new Date(),"Virtual Gold Holding data added successfully");
	}

	@Override
	public SuccessResponse updateVirtualGoldHolding(int holdingId, VirtualGoldHoldingDTO holdingDto)
			throws VirtualGoldHoldingNotFoundException,UserNotFoundException, VendorBranchNotFoundException {
		User user = userService.getUserByUserId(holdingDto.getUserId());
		VendorBranch branch=branchService.getVendorBranchByBranchId(holdingDto.getBranchId());
		VirtualGoldHolding virtualGoldHolding=getVirtualGoldHoldingById(holdingId);
		virtualGoldHolding.setBranch(branch);
		virtualGoldHolding.setUser(user);
		virtualGoldHolding.setQuantity(holdingDto.getQuantity());
		virtualGoldHoldingRepository.save(virtualGoldHolding);
		return new SuccessResponse(new Date(),"Virtual Gold Holding data updated successfully");
	}
}
