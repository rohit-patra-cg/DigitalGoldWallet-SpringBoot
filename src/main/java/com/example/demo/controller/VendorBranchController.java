package com.example.demo.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.VendorBranch;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.service.VendorBranchService;

@RestController
@RequestMapping("/api/v1/vendor_branches")
public class VendorBranchController {
	
	    @Autowired
	    private VendorBranchService vendorBranchService;

	    @GetMapping
	    ResponseEntity<List<VendorBranch>> getAllVendorBranches() {
	        return ResponseEntity.ok(vendorBranchService.getAllVendorBranches());
	    }

	    @GetMapping("/{branch_id}")
	    ResponseEntity<VendorBranch> getVendorBranchById(@PathVariable("branch_id") int branchId) throws VendorBranchNotFoundException {
	        return ResponseEntity.ok(vendorBranchService.getVendorBranchByBranchId(branchId));
	    }

	    @GetMapping("/by_vendor/{vendor_id}")
	    ResponseEntity<List<VendorBranch>> getVendorBranchesByVendorId(@PathVariable("vendor_id") int vendorId) throws VendorBranchNotFoundException {
	        return ResponseEntity.ok(vendorBranchService.getVendorBranchByVendorId(vendorId));
	    }

	    @GetMapping("/by_city/{city}")
	    ResponseEntity<List<VendorBranch>> getVendorBranchesByCity(@PathVariable("city") String city) {
	        return ResponseEntity.ok(vendorBranchService.getVendorBranchByCity(city));
	    }

	    @GetMapping("/by_state/{state}")
	    ResponseEntity<List<VendorBranch>> getVendorBranchesByState(@PathVariable("state") String state) {
	        return ResponseEntity.ok(vendorBranchService.getVendorBranchByState(state));
	    }

	    @GetMapping("/by_country/{country}")
	    ResponseEntity<List<VendorBranch>> getVendorBranchesByCountry(@PathVariable("country") String country) {
	        return ResponseEntity.ok(vendorBranchService.getVendorBranchByCountry(country));
	    }

	    @GetMapping("/transactions/{branch_id}")
	    ResponseEntity<List<TransactionHistory>> getVendorBranchTransactions(@PathVariable("branch_id") int branchId) throws VendorBranchNotFoundException {
	        return ResponseEntity.ok(vendorBranchService.getVendorBranchTransactionsByBranchId(branchId));
	    }
	}
	
	

	
	
	
	
	
	
	
	
	



