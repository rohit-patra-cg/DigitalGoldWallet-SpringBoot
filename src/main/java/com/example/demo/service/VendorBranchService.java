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
	List<VendorBranch> getAllVendorBranches();

	VendorBranch getVendorBranchByBranchId(int branchId) throws VendorBranchNotFoundException;

	List<VendorBranch> getVendorBranchByVendorId(int vendorId) throws VendorBranchNotFoundException;

	List<VendorBranch> getVendorBranchByCity(String city);

	List<VendorBranch> getVendorBranchByState(String state);

	List<VendorBranch> getVendorBranchByCountry(String country);

	List<TransactionHistory> getVendorBranchTransactionsByBranchId(int branchId) throws VendorBranchNotFoundException;

	SuccessResponse addVendorBranch(VendorBranchDTO vendorBranchDTO) throws VendorNotFoundException, AddressNotFoundException;

	SuccessResponse transferGoldBetweenBranches(int sourceBranchId, int destinationBranchId, double quantity)throws VendorBranchNotFoundException, InvalidGoldQuantityException;
	
	SuccessResponse updateVendorBranch(int branchId, VendorBranchDTO vendorBranchDTO) throws VendorBranchNotFoundException, AddressNotFoundException, VendorNotFoundException;
}
