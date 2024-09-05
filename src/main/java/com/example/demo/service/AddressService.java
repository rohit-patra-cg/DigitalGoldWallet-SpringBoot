package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Address;
import com.example.demo.exception.AddressNotFoundException;


public interface AddressService {
	
	/**
	 * Get All Addresses
	 * @return List<Address> Collection of Addresses
	 */
	List<Address> getAllAddresses();
	
	/**
	 * Get Address by address_id
	 * @param addressId
	 * @return Address Object 
	 * @throws AddressNotFoundException
	 */
	Address getAddressByAddressId(int addressId) throws AddressNotFoundException;
	
	/**
	 * Add New Address
	 * @param address
	 * @return SuccessResponse Response for successfully adding a address
	 */
	SuccessResponse createAddress(Address address);
	
	/**
	 * Update Address by address_id
	 * @param addressId, address
	 * @return SuccessResponse Response for updating user's address 
	 * @throws AddressNotFoundException
	 */
	SuccessResponse updateAddress(int addressId, Address address) throws AddressNotFoundException;
}
