package com.example.demo.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.example.demo.entity.Address;
import com.example.demo.exception.AddressNotFoundException;


public interface AddressService {
	
	List<Address> getAllAddresses();
	
	Address getAddressByAddressId(int addressId) throws AddressNotFoundException;
	
}
