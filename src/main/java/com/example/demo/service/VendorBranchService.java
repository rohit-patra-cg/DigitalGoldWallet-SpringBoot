package com.example.demo.service;

import java.util.List;

import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.VendorBranch;
import com.example.demo.exception.VendorBranchNotFoundException;

public interface VendorBranchService {
	List<VendorBranch>getAllVendorBranches();
	VendorBranch getVendorBranchByBranchId(int branchId)throws VendorBranchNotFoundException;
	List<VendorBranch> getVendorBranchByVendorId(int vendorId) throws VendorBranchNotFoundException;
	List<VendorBranch> getVendorBranchByCity(String city); 
	List<VendorBranch> getVendorBranchByState(String state); 
	List<VendorBranch> getVendorBranchByCountry(String country); 
	List<TransactionHistory> getVendorBranchTransactionsByBranchId(int branchId)throws VendorBranchNotFoundException; 
}
