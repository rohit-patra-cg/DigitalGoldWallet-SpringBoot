package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.Address;
import com.example.demo.entity.VendorBranch;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.repository.VendorBranchRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class VendorBranchServiceImplTest {

	@Mock
	VendorBranchRepository vendorBranchRepository;

	@InjectMocks
	VendorBranchServiceImpl vendorBranchServiceImpl;

	@Test
	void testGetAllVendorBranches() {

		VendorBranch b1 = new VendorBranch();
		VendorBranch b2 = new VendorBranch();
		List<VendorBranch> vendorBranches = Arrays.asList(b1, b2);
		when(vendorBranchRepository.findAll()).thenReturn(vendorBranches);
		List<VendorBranch> result = vendorBranchServiceImpl.getAllVendorBranches();
		assertAll(() -> assertEquals(2, result.size()), () -> assertEquals(b1, result.get(0)),
				() -> assertEquals(b2, result.get(1)));
	}

	@Test
	void testGetVendorBranchByBranchId_BranchFound() throws VendorBranchNotFoundException {

		int branchId = 1;
		VendorBranch branch = new VendorBranch();
		branch.setBranchId(branchId);

		when(vendorBranchRepository.findById(branchId)).thenReturn(Optional.of(branch));

		VendorBranch result = vendorBranchServiceImpl.getVendorBranchByBranchId(branchId);
		assertEquals(branchId, result.getBranchId());
	}

	@Test
	void testGetVendorBranchByCity_BranchesFound() {

		String city = "New York";
		VendorBranch branch1 = new VendorBranch();
		branch1.setBranchId(1);
		branch1.setAddress(new Address());
		VendorBranch branch2 = new VendorBranch();
		branch2.setBranchId(2);
		branch2.setAddress(new Address());

		List<VendorBranch> branches = Arrays.asList(branch1, branch2);
		when(vendorBranchRepository.findByCity(city)).thenReturn(branches);
		List<VendorBranch> result = vendorBranchServiceImpl.getVendorBranchByCity(city);

		assertEquals(2, result.size());
		assertEquals(branch1.getBranchId(), result.get(0).getBranchId());
		assertEquals(branch2.getBranchId(), result.get(1).getBranchId());
	}

	@Test
	void testGetVendorBranchByState_BranchesFound() {

		String state = "California";
		VendorBranch branch1 = new VendorBranch();
		branch1.setBranchId(1);
		branch1.setAddress(new Address());
		VendorBranch branch2 = new VendorBranch();
		branch2.setBranchId(2);
		branch2.setAddress(new Address());
		List<VendorBranch> branches = Arrays.asList(branch1, branch2);

		when(vendorBranchRepository.findByState(state)).thenReturn(branches);
		List<VendorBranch> result = vendorBranchServiceImpl.getVendorBranchByState(state);

		assertEquals(2, result.size());
		assertEquals(branch1.getBranchId(), result.get(0).getBranchId());
		assertEquals(branch2.getBranchId(), result.get(1).getBranchId());
	}

	@Test
	void testGetVendorBranchByCountry_BranchesFound() {

		String country = "USA";
		VendorBranch branch1 = new VendorBranch();
		branch1.setBranchId(1);
		branch1.setAddress(new Address()); // Assuming Address is set with country = "USA"
		VendorBranch branch2 = new VendorBranch();
		branch2.setBranchId(2);
		branch2.setAddress(new Address()); // Assuming Address is set with country = "USA"
		List<VendorBranch> branches = Arrays.asList(branch1, branch2);

		when(vendorBranchRepository.findByCountry(country)).thenReturn(branches);

		List<VendorBranch> result = vendorBranchServiceImpl.getVendorBranchByCountry(country);

		assertEquals(2, result.size());
		assertEquals(branch1.getBranchId(), result.get(0).getBranchId());
		assertEquals(branch2.getBranchId(), result.get(1).getBranchId());
	}

}
