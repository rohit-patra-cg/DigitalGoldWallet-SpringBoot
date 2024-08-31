package com.example.demo.service;

import java.util.List;

import com.example.demo.dto.SuccessResponse;
import com.example.demo.entity.Address;
import com.example.demo.exception.AddressNotFoundException;


public interface AddressService {
	List<Address> getAllAddresses();
	Address getAddressByAddressId(int addressId) throws AddressNotFoundException;
	SuccessResponse createAddress(Address address);
	SuccessResponse updateAddress(int addressId, Address address) throws AddressNotFoundException;
}
