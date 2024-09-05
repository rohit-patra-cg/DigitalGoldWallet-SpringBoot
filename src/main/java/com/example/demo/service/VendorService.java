package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Vendor;
import com.example.demo.exception.VendorAlreadyExistsException;
import com.example.demo.exception.VendorNotFoundException;

public interface VendorService {
	
	/**
	 * Get all Vendors
	 * @return List<Vendor> Collection of Vendors
	 */
	List<Vendor> getAllVendors();
    
	/**
	 * Get Vendor by vendor_id
	 * @param id 
	 * @return Vendor Object
	 * @throws VendorNotFoundException
	 */
	Vendor getVendorById(int id) throws VendorNotFoundException;
    
	/**
	 * Get Vendor by vendor_name
	 * @param name
	 * @return Vendor Object
	 * @throws VendorNotFoundException
	 */
	Vendor getVendorByName(String name) throws VendorNotFoundException;
    
	/**
	 * Add New Vendor
	 * @param vendor
	 * @return SuccessResponse Response for successfully adding a new vendor
	 * @throws VendorAlreadyExistsException 
	 */
	SuccessResponse addVendor(Vendor vendor) throws VendorAlreadyExistsException;
    
	/**
	 * Update Vendor by vendor_id
	 * @param vendorId, vendor
	 * @return SuccessResponse Response for successfully updating vendor
	 * @throws VendorNotFoundException
	 */
	SuccessResponse updateVendor(int vendorId, Vendor vendor) throws VendorNotFoundException;
     
	/**
	 * Update Vendor total_gold_quantity by vendor_id
	 * @param vendorId, quantity
	 * @return SuccessResponse Response for successfully updating total gold quantity.
	 * @throws VendorNotFoundException
	 */
	SuccessResponse updateTotalGoldQuantity(int vendorId, Double quantity) throws VendorNotFoundException;
    
	/**
	 * Update All Vendor Current Gold Price with new_price
	 * @param vendorId, newPrice
	 * @return SuccessResponse Response for successfully updating current gold price
	 * @throws VendorNotFoundException
	 */
	SuccessResponse updateCurrentGoldPrice(int vendorId, Double newPrice) throws VendorNotFoundException;
}
