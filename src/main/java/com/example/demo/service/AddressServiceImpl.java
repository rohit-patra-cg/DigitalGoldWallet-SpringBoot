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
	
	/**
	 * Get All Addresses
	 * @return List<Address> Collection of Addresses
	 */
	@Override
	public List<Address> getAllAddresses() {
		return addressRepository.findAll();
	}
	
	/**
	 * Get Address by address_id
	 * @param addressId
	 * @return Address Object 
	 * @throws AddressNotFoundException
	 */
	@Override
	public Address getAddressByAddressId(int addressId) throws AddressNotFoundException {
		return addressRepository.findById(addressId).orElseThrow(() -> new AddressNotFoundException("Address#" + addressId + " not found."));
	}
	
	/**
	 * Add New Address
	 * @param address
	 * @return SuccessResponse Response for successfully adding a address
	 */
	@Override
	public SuccessResponse createAddress(Address address) {
		Address savedAddress = addressRepository.save(address);
		return new SuccessResponse(new Date(), "Address added successfully", savedAddress.getAddressId());
	}
	
	/**
	 * Update Address by address_id
	 * @param addressId, address
	 * @return SuccessResponse Response for updating user's address 
	 * @throws AddressNotFoundException
	 */
	@Override
	public SuccessResponse updateAddress(int addressId, Address address) throws AddressNotFoundException {
		Address addressById = getAddressByAddressId(addressId);
		addressById.setCity(address.getCity());
		addressById.setState(address.getState());
		addressById.setCountry(address.getCountry());
		addressById.setStreet(address.getStreet());
		addressById.setPostalCode(address.getPostalCode());
		addressRepository.save(addressById);
		return new SuccessResponse(new Date(), "Address updated successfully", addressId);
	}

}
