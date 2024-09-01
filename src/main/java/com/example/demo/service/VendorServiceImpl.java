package com.example.demo.service;
import java.util.Date;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Vendor;
import com.example.demo.exception.VendorAlreadyExistsException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.repository.VendorRepository;

@Service
public class VendorServiceImpl implements VendorService {

    @Autowired
    private VendorRepository vendorRepository;

    @Override
    public List<Vendor> getAllVendors() {
        return vendorRepository.findAll();
    }

    @Override
    public Vendor getVendorById(int id) throws VendorNotFoundException {
        return vendorRepository.findById(id)
                .orElseThrow(() -> new VendorNotFoundException("Vendor#" + id + " not found."));
    }

    @Override
    public Vendor getVendorByName(String name) throws VendorNotFoundException {
        return vendorRepository.findByVendorName(name)
                .orElseThrow(() -> new VendorNotFoundException("Vendor with name " + name + " not found."));
    }

    @Override
    public SuccessResponse addVendor(Vendor vendor) throws  VendorAlreadyExistsException {
        vendorRepository.save(vendor);
        return new SuccessResponse(new Date(), "Vendor details added successfully");
    }

    @Override
    public Vendor updateVendor(int id, Vendor updatedVendor) throws VendorNotFoundException {
        Vendor vendor = getVendorById(id);
        vendor.setVendorName(updatedVendor.getVendorName()); 
        vendor.setDescription(updatedVendor.getDescription());
        vendor.setContactPersonName(updatedVendor.getContactPersonName());
        vendor.setContactEmail(updatedVendor.getContactEmail());
        vendor.setContactPhone(updatedVendor.getContactPhone());
        vendor.setWebsiteUrl(updatedVendor.getWebsiteUrl());
        vendor.setTotalGoldQuantity(updatedVendor.getTotalGoldQuantity());
        vendor.setCurrentGoldPrice(updatedVendor.getCurrentGoldPrice());
        return vendorRepository.save(vendor);
    }

    @Override
    public void deleteVendor(int id) throws VendorNotFoundException {
        Vendor vendor = getVendorById(id);
        vendorRepository.delete(vendor);
    }
}
