package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.dto.VendorBranchDTO;
import com.example.demo.entity.TransactionHistory;
import com.example.demo.entity.VendorBranch;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.exception.InvalidGoldQuantityException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.exception.VendorNotFoundException;
import com.example.demo.repository.TransactionHistoryRepository;
import com.example.demo.repository.VendorBranchRepository;

@Service
public class VendorBranchServiceImpl implements VendorBranchService {

	private VendorBranchRepository vendorBranchRepository;

	private TransactionHistoryRepository transactionHistoryRepository;
	
	private AddressService addressService;
	
	private VendorService vendorService;

	public VendorBranchServiceImpl(VendorBranchRepository vendorBranchRepository,
			TransactionHistoryRepository transactionHistoryRepository, AddressService addressService,
			VendorService vendorService) {
		this.vendorBranchRepository = vendorBranchRepository;
		this.transactionHistoryRepository = transactionHistoryRepository;
		this.addressService = addressService;
		this.vendorService = vendorService;
	}

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
		getVendorBranchByBranchId(branchId);
		return transactionHistoryRepository.findAllByBranchId(branchId);
	}
	
	@Override
    public SuccessResponse addVendorBranch(VendorBranchDTO branchDTO) throws VendorNotFoundException, AddressNotFoundException {
		VendorBranch vendorBranch = new VendorBranch();
		vendorBranch.setVendor(vendorService.getVendorById(branchDTO.getVendorId()));
		vendorBranch.setAddress(addressService.getAddressByAddressId(branchDTO.getAddressId()));
		vendorBranch.setQuantity(branchDTO.getQuantity());
		VendorBranch savedBranch = vendorBranchRepository.save(vendorBranch);
		return new SuccessResponse(new Date(), "Vendor Branch added successfully", savedBranch.getBranchId());
    }

    @Override
    public SuccessResponse transferGoldBetweenBranches(int sourceBranchId, int destinationBranchId, double quantity) throws VendorBranchNotFoundException, InvalidGoldQuantityException {
        VendorBranch sourceBranch = getVendorBranchByBranchId(sourceBranchId);
        VendorBranch destinationBranch = getVendorBranchByBranchId(destinationBranchId);
        if (sourceBranch.getQuantity() < quantity) {
            throw new InvalidGoldQuantityException("Insufficient gold in the source branch");
        }
        sourceBranch.setQuantity(sourceBranch.getQuantity() - quantity);
        destinationBranch.setQuantity(destinationBranch.getQuantity() + quantity);
        vendorBranchRepository.save(sourceBranch);
        vendorBranchRepository.save(destinationBranch);
        return new SuccessResponse(new Date(),"Vendor Branch transfer was successful", sourceBranchId);
    }

    @Override
    public SuccessResponse updateVendorBranch(int branchId, VendorBranchDTO vendorBranchDTO) throws VendorBranchNotFoundException, AddressNotFoundException, VendorNotFoundException {
        VendorBranch vendorBranch = getVendorBranchByBranchId(branchId);
        vendorBranch.setQuantity(vendorBranchDTO.getQuantity());
        vendorBranch.setAddress(addressService.getAddressByAddressId(vendorBranchDTO.getAddressId()));
        vendorBranch.setVendor(vendorService.getVendorById(vendorBranchDTO.getVendorId()));
        vendorBranchRepository.save(vendorBranch);
        return new SuccessResponse(new Date(),"Vendor Branch updated successfully", branchId);
    }

}
