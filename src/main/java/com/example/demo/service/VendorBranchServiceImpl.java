package com.example.demo.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.VendorBranch;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.repository.TransactionHistoryRepository;
import com.example.demo.repository.VendorBranchRepository;

@Service
public class VendorBranchServiceImpl implements VendorBranchService {

	@Autowired
	private VendorBranchRepository vendorBranchRepository;

	@Autowired
	private TransactionHistoryRepository transactionHistoryRepository;

	@Override
	public List<VendorBranch> getAllVendorBranches() {
		return vendorBranchRepository.findAll();
	}

	@Override
	public VendorBranch getVendorBranchByBranchId(int branchId) throws VendorBranchNotFoundException {
		return vendorBranchRepository.findById(branchId)
				.orElseThrow(() -> new VendorBranchNotFoundException("Vendor Branch not found with id: " + branchId));
	}

	@Override
	public List<VendorBranch> getVendorBranchByVendorId(int vendorId) throws VendorBranchNotFoundException {
		List<VendorBranch> branches = vendorBranchRepository.findByBranchId(vendorId);
		if (branches.isEmpty()) {
			throw new VendorBranchNotFoundException("No Vendor Branches found for Vendor with id: " + vendorId);
		}
		return branches;
	}

	@Override
	public List<VendorBranch> getVendorBranchByCity(String city) {
		return vendorBranchRepository.findByCity(city);
	}

	@Override
	public List<VendorBranch> getVendorBranchByState(String state) {
		return vendorBranchRepository.findByState(state);
	}

	@Override
	public List<VendorBranch> getVendorBranchByCountry(String country) {
		return vendorBranchRepository.findByCountry(country);
	}

	@Override
	public List<TransactionHistory> getVendorBranchTransactionsByBranchId(int branchId)
			throws VendorBranchNotFoundException {
		vendorBranchRepository.findById(branchId)
				.orElseThrow(() -> new VendorBranchNotFoundException("Vendor Branch not found with id: " + branchId));
		return transactionHistoryRepository.findAllByBranchId(branchId);
	}

}
