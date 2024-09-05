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
	 * @return List<Vendor> Collection of Vendors
	 */
	@Override
	public List<Vendor> getAllVendors() {
		return vendorRepository.findAll();
	}
   
	/**
	 * Get Vendor by vendor_id
	 * @param id 
	 * @return Vendor Object
	 * @throws VendorNotFoundException
	 */
	@Override
	public Vendor getVendorById(int id) throws VendorNotFoundException {
		return vendorRepository.findById(id)
				.orElseThrow(() -> new VendorNotFoundException("Vendor#" + id + " not found."));
	}
	
	/**
	 * Get Vendor by vendor_name
	 * @param name
	 * @return Vendor Object
	 * @throws VendorNotFoundException
	 */
	@Override
	public Vendor getVendorByName(String name) throws VendorNotFoundException {
		return vendorRepository.findByVendorName(name)
				.orElseThrow(() -> new VendorNotFoundException("Vendor with name " + name + " not found."));
	}
	
	/**
	 * Add New Vendor
	 * @param vendor
	 * @return SuccessResponse Response for successfully adding a new vendor
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
	 * Update Vendor by vendor_id
	 * @param vendorId, vendor
	 * @return SuccessResponse Response for successfully updating vendor
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
	 * Update Vendor total_gold_quantity by vendor_id
	 * @param vendorId, quantity
	 * @return SuccessResponse Response for successfully updating total gold quantity.
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
	 * Update All Vendor Current Gold Price with new_price
	 * @param vendorId, newPrice
	 * @return SuccessResponse Response for successfully updating current gold price
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
