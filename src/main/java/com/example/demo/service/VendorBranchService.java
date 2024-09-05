package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.VendorBranchDTO;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.VendorBranch;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.exception.InvalidGoldQuantityException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.exception.VendorNotFoundException;

public interface VendorBranchService {
	/**
	 *Get All Vendor Branches
	 *@return List<VendorBranch> Collection of VendorBranch
	 */
	List<VendorBranch> getAllVendorBranches();

	/**
	 *Get Vendor Branch by branch_id
	 *@param VendorBranch
	 *@return VendorBranch Object
	 *@throws VendorBranchNotFoundException
	 */
	VendorBranch getVendorBranchByBranchId(int branchId) throws VendorBranchNotFoundException;
   
	/**
	 * Get Vendor Branch by vendor_id
	 * @param vendorId 
	 * @return List<VendorBranch> Collection of VendorBranch 
	 * @throws VendorBranchNotFoundException
	 */
	List<VendorBranch> getVendorBranchByVendorId(int vendorId) throws VendorBranchNotFoundException;

	/**
	 *Get Vendor Branch by city
	 *@param city
	 *@return List<VendorBranch> Collection of VendorBranch
	 */
	List<VendorBranch> getVendorBranchByCity(String city);
    
	/**
	 *Get Vendor Branch by state
	 *@param state
	 *@return List<VendorBranch> Collection of VendorBranch
	 */
	List<VendorBranch> getVendorBranchByState(String state);
    
	/**
	 *Get Vendor Branch by country
	 *@param country
	 *@return List<VendorBranch> Collection of VendorBranch
	 */
	List<VendorBranch> getVendorBranchByCountry(String country);
    
	/**
	 * Get Vendor Branch transactions by branch_id
	 * @param branchId
	 * @return List<TransactionHistory> Collection of transactionHistory
	 * @throws VendorBranchNotFoundException
	 */
	List<TransactionHistory> getVendorBranchTransactionsByBranchId(int branchId) throws VendorBranchNotFoundException;
   
	/**
	 * Add New Vendor Branch
	 * @param branchDTO
	 * @return SuccessResponse Response for successfully adding a new vendor branch
	 * @throws VendorBranchNotFoundException
	 * @throws AddressNotFoundException
	 */
	SuccessResponse addVendorBranch(VendorBranchDTO vendorBranchDTO) throws VendorNotFoundException, AddressNotFoundException;
    
	/**
	 * Transfers a specified quantity of gold from one vendor branch to another
	 * @param sourceBranchId, destinationBranchId, quantity
	 * @return SuccessResponse Response for successfully transferring gold between branches
	 * @throws VendorBranchNotFoundException 
	 * @throws InvalidGoldQuantityException
	 */
	SuccessResponse transferGoldBetweenBranches(int sourceBranchId, int destinationBranchId, double quantity)throws VendorBranchNotFoundException, InvalidGoldQuantityException;
	
     /**
	  * Update Vendorbranch details by branch_id
	  * @param branchId, vendorBranchDTO
	  * @return SuccessResponse Response for successfully updating Vendor Branch
	  * @throws VendorBranchNotFoundException
	  * @throws AddressNotFoundException
	  * @throws VendorNotFoundException
	  */
	SuccessResponse updateVendorBranch(int branchId, VendorBranchDTO vendorBranchDTO) throws VendorBranchNotFoundException, AddressNotFoundException, VendorNotFoundException;
}
