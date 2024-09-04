package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Vendor;
import com.example.demo.exception.VendorAlreadyExistsException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {
	
	private VendorRepository vendorRepository;

	public VendorServiceImpl(VendorRepository vendorRepository) {
		this.vendorRepository = vendorRepository;
	}
	
	/**
	 * Get all Vendors
	 * @return List<Vendor> collection of All vendors
	 */

	@Override
	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}
   
	/**
	 * Get Vendor By Id
	 * @param id The ID of the Vendor to be retrieved.
	 * @return The Vendor entity associated with the specified ID
	 * @throws VendorBranchNotFoundException
	 */
	@Override
	public Vendor getVendorById(int id) throws VendorNotFoundException {
		return vendorRepository.findById(id)
				.orElseThrow(() -> new VendorNotFoundException("Vendor#" + id + " not found."));
	}
	
	/**
	 * Retrieves a Vendor entity by its name
	 * @param name
	 * @return The Vendor entity associated with the specified name.
	 * @throws VendorNotFoundException
	 */

	@Override
	public Vendor getVendorByName(String name) throws VendorNotFoundException {
		return vendorRepository.findByVendorName(name)
				.orElseThrow(() -> new VendorNotFoundException("Vendor with name " + name + " not found."));
	}
	/**
	 * Adds new Vendor
	 * @param vendor
	 * @return A SuccessResponse indicating the result of the add operation.
	 * @throws VendorAlreadyExistsException 
	 */

	@Override
	public SuccessResponse addVendor(Vendor vendor) throws VendorAlreadyExistsException {
		if (vendorRepository.findByContactEmail(vendor.getContactEmail()).isEmpty()) {
			Vendor savedVendor = vendorRepository.save(vendor);
			return new SuccessResponse(new Date(), "Vendor details added successfully", savedVendor.getVendorId());
		}
		throw new VendorAlreadyExistsException("Vendor Already Exists");
	}
	
	/**
	 * Update Vendor by vendorId
	 * @param vendorId,vendor
	 * @return A SuccessResponse indicating the result of the update operation
	 * @throws VendorNotFoundException
	 */

	@Override
	public SuccessResponse updateVendor(int vendorId, Vendor vendor) throws VendorNotFoundException {

		Vendor existingVendor = vendorRepository.findById(vendorId)
				.orElseThrow(() -> new VendorNotFoundException("Vendor not found with id: " + vendorId));
		existingVendor.setVendorName(vendor.getVendorName());
		existingVendor.setDescription(vendor.getDescription());
		existingVendor.setContactPersonName(vendor.getContactPersonName());
		existingVendor.setContactEmail(vendor.getContactEmail());
		existingVendor.setContactPhone(vendor.getContactPhone());
		existingVendor.setWebsiteUrl(vendor.getWebsiteUrl());
		existingVendor.setTotalGoldQuantity(vendor.getTotalGoldQuantity());
		existingVendor.setCurrentGoldPrice(vendor.getCurrentGoldPrice());
		vendorRepository.save(existingVendor);
		return new SuccessResponse(new Date(), "Vendor details updated successfully", vendorId);
	}
     
	/**
	 * Update Vendor totalGoldQuantity by vendorId
	 * @param vendorId,quantity
	 * @return A SuccessResponse indicating the result of the update operation.
	 * @throws VendorNotFoundException
	 */
	@Override
	public SuccessResponse updateTotalGoldQuantity(int vendorId, Double quantity) throws VendorNotFoundException {
		Vendor existingVendor = getVendorById(vendorId);
		existingVendor.setTotalGoldQuantity(quantity);
		vendorRepository.save(existingVendor);
		return new SuccessResponse(new Date(), "Total gold quantity updated successfully", vendorId);
	}
	/**
	 * Update All Vendor Current Gold Price with new price
	 * @param vendorId,newPrice
	 * @return A SuccessResponse indicating the result of the update operation.
	 * @throws VendorNotFoundException
	 */

	@Override
	public SuccessResponse updateCurrentGoldPrice(int vendorId, Double newPrice) throws VendorNotFoundException {
		Vendor existingVendor = getVendorById(vendorId);
		existingVendor.setCurrentGoldPrice(newPrice);
		vendorRepository.save(existingVendor);
		return new SuccessResponse(new Date(), "Current gold price updated successfully", vendorId);
	}

}
