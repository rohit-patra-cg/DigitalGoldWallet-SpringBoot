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
	
	/**
	 * Get All Virtual Gold Holding
	 * @return List<VirtualGoldHolding> Collection of VirtualGoldHolding
	 */
	List<VirtualGoldHolding> getAllVirtualGoldHoldings();
	
	/**
	 * Get Virtual Gold Holding by holding_id
	 * @param holdingId
	 * @return VirtualGoldHolding Object
	 * @throws virtualGoldHoldingNotFoundException
	 */
	VirtualGoldHolding getVirtualGoldHoldingById(int holdingId) throws VirtualGoldHoldingNotFoundException;
	
	/**
	 * Get All Virtual Gold Holding by users_id
	 * @param userId
	 * @return Collection of VirtualGoldHolding
	 * @throws UserNotFoundException
	 */
	List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserId(int userId) throws UserNotFoundException;
	
	/**
	 * Get Virtual Gold Holding for a specific user and vendor
	 * @param userId, vendorId
	 * @return Collection of VirtualGoldHolding 
	 * @throws UserNotFoundException
	 * @throws VendorNotFoundException
	 */
	List<VirtualGoldHolding> getAllVirtualGoldHoldingsByUserIdAndVendorId(int userId, int vendorId) throws UserNotFoundException, VendorNotFoundException;
	
	/**
	 * Add New Virtual Gold Holding
	 * @param  holdingDto 
	 * @return SuccessResponse Response for successfully adding new virtual gold holding
	 * @throws VirtualGoldHoldingAreadyExistsException
	 * @throws UserNotFoundException 
	 * @throws VendorBranchNotFoundException
     */
	SuccessResponse addVirtualGoldHolding(VirtualGoldHoldingDTO virtualGoldHoldingdto) throws VirtualGoldHoldingAreadyExistsException, UserNotFoundException, VendorBranchNotFoundException;
	
	/**
	 * Update Virtual Gold Holding by holding_id
	 * @Param holdingId, holdingDto
	 * @return SuccessResponse Response for successfully updating virtual gold holding
	 * @throws VirtualGoldHoldingNotFoundException 
	 * @throws UserNotFoundException
	 * @throws VendorBranchNotFoundException
	 */
	SuccessResponse updateVirtualGoldHolding(int holdingId, VirtualGoldHoldingDTO holdingDto)throws VirtualGoldHoldingNotFoundException, UserNotFoundException, VendorBranchNotFoundException;
	
	 /**
     * Convert Virtual Gold Holding to Physical gold holding 
     * @param quantity,  holdingId
     * @return SuccessResponse Response for successfully converting virtual gold holding to physical gold holding 
     * @throws VirtualGoldHoldingNotFoundException 
     * @throws UserNotFoundException 
     * @throws VendorBranchNotFoundException 
     * @throws InvalidGoldQuantityException
     */
	SuccessResponse convertToPysical(double quantity, int holdingId) throws VirtualGoldHoldingNotFoundException, UserNotFoundException, VendorBranchNotFoundException, InvalidGoldQuantityException;
}
