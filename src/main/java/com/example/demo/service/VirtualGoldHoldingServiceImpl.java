package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.VirtualGoldHolding;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.exception.VirtualGoldHoldingNotFoundException;
import com.example.demo.repository.VirtualGoldHoldingRepository;

@Service
public class VirtualGoldHoldingServiceImpl implements VirtualGoldHoldingService {

	@Autowired
	private VirtualGoldHoldingRepository virtualGoldHoldingRepository;
	
	@Autowired
	private UserService userService;

	@Autowired
	private VendorService vendorService;
	
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

}
