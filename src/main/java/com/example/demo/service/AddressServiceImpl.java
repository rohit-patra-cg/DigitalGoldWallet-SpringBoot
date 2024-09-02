package com.example.demo.service;

import java.util.Date;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Address;
import com.example.demo.exception.AddressNotFoundException;
import com.example.demo.repository.AddressRepository;

@Service
public class AddressServiceImpl implements AddressService {

	private AddressRepository addressRepository;

	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}

	@Override
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}

	@Override
	public Address getAddressByAddressId(int addressId) throws AddressNotFoundException {
		return addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address#" + addressId + " not found."));
	}

	@Override
	public SuccessResponse createAddress(Address address) {
		addressRepository.save(address);
		return new SuccessResponse(new Date(), "Address added successfully");
	}

	@Override
	public SuccessResponse updateAddress(int addressId, Address address) throws AddressNotFoundException {
		Address addressById = getAddressByAddressId(addressId);
		addressById.setCity(address.getCity());
		addressById.setState(address.getState());
		addressById.setCountry(address.getCountry());
		addressById.setStreet(address.getStreet());
		addressById.setPostalCode(address.getPostalCode());
		addressRepository.save(addressById);
		return new SuccessResponse(new Date(), "Address updated successfully");
	}

}
