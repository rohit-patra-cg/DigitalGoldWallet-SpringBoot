package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.VirtualGoldHoldingDTO;
import com.example.demo.entity.VirtualGoldHolding;
import com.example.demo.exception.InvalidGoldQuantityException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.exception.VirtualGoldHoldingAreadyExistsException;
import com.example.demo.exception.VirtualGoldHoldingNotFoundException;

public interface VirtualGoldHoldingService {
	List<VirtualGoldHolding> getAllVirtualGoldHoldings();
	VirtualGoldHolding getVirtualGoldHoldingById(int holdingId) throws VirtualGoldHoldingNotFoundException;
	List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserId(int userId) throws UserNotFoundException;
	List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserIdAndVendorId(int userId, int vendorId) throws UserNotFoundException, VendorNotFoundException;
	SuccessResponse addVirtualGoldHolding(VirtualGoldHoldingDTO virtualGoldHoldingdto) throws VirtualGoldHoldingAreadyExistsException, UserNotFoundException, VendorBranchNotFoundException;
	SuccessResponse updateVirtualGoldHolding(int holdingId, VirtualGoldHoldingDTO holdingDto)throws VirtualGoldHoldingNotFoundException, UserNotFoundException, VendorBranchNotFoundException;
	SuccessResponse convertToPysical(double quantity, int holdingId) throws VirtualGoldHoldingNotFoundException, UserNotFoundException, VendorBranchNotFoundException, InvalidGoldQuantityException;
}
