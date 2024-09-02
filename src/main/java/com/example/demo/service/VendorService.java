package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Vendor;
import com.example.demo.exception.VendorAlreadyExistsException;
import com.example.demo.exception.VendorNotFoundException;

public interface VendorService {
	List<Vendor> getAllVendors();

	Vendor getVendorById(int id) throws VendorNotFoundException;

	Vendor getVendorByName(String name) throws VendorNotFoundException;

	SuccessResponse addVendor(Vendor vendor) throws VendorAlreadyExistsException;

	SuccessResponse updateVendor(int vendorId, Vendor vendor) throws VendorNotFoundException;

	SuccessResponse updateTotalGoldQuantity(int vendorId, Double quantity) throws VendorNotFoundException;

	SuccessResponse updateCurrentGoldPrice(int vendorId, Double newPrice) throws VendorNotFoundException;
}
