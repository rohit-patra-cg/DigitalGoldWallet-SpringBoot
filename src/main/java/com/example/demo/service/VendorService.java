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

	Vendor updateVendor(int id, Vendor vendor) throws VendorNotFoundException;

	SuccessResponse addVendor(Vendor vendor) throws VendorAlreadyExistsException;

}
