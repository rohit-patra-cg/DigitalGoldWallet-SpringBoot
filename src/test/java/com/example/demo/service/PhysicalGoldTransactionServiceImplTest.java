package com.example.demo.service;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.when;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import com.example.demo.entity.PhysicalGoldTransaction;
import com.example.demo.entity.User;
import com.example.demo.entity.VendorBranch;
import com.example.demo.exception.PhysicalGoldTransactionNotFoundException;
import com.example.demo.exception.UserNotFoundException;
import com.example.demo.exception.VendorBranchNotFoundException;
import com.example.demo.repository.PhysicalGoldTransactionRepository;

@ExtendWith(MockitoExtension.class)
class PhysicalGoldTransactionServiceImplTest {

	@Mock
	private PhysicalGoldTransactionRepository physicalGoldTransactionRepository;

	@Mock
	private UserService userService;

	@Mock
	private AddressService addressService;

	@Mock
	private VendorBranchService vendorBranchService;

	@InjectMocks
	private PhysicalGoldTransactionServiceImpl physicalGoldTransactionServiceImpl;

	@Test
	void testGetAllPhysicalGoldTransactions() {
		PhysicalGoldTransaction t1 = new PhysicalGoldTransaction();
		PhysicalGoldTransaction t2 = new PhysicalGoldTransaction();
		List<PhysicalGoldTransaction> transactions = Arrays.asList(t1, t2);

		when(physicalGoldTransactionRepository.findAll()).thenReturn(transactions);
		List<PhysicalGoldTransaction> result = physicalGoldTransactionServiceImpl.getAllPhysicalGoldTransactions();

		assertAll(() -> assertEquals(2, result.size()), () -> assertEquals(t1, result.get(0)),
				() -> assertEquals(t2, result.get(1)));
	}

	@Test
	void testGetPhysicalGoldTransactionById_TransactionFound() throws PhysicalGoldTransactionNotFoundException {
		int transactionId = 1;
		PhysicalGoldTransaction transaction = new PhysicalGoldTransaction();
		transaction.setTransactionId(transactionId);

		when(physicalGoldTransactionRepository.findById(transactionId)).thenReturn(Optional.of(transaction));
		PhysicalGoldTransaction foundTransaction = physicalGoldTransactionServiceImpl
				.getPhysicalGoldTransactionById(transactionId);

		assertEquals(transactionId, foundTransaction.getTransactionId());
	}

	@Test
	void testGetPhysicalGoldTransactionById_TransactionNotFound() {
		int transactionId = 1;

		when(physicalGoldTransactionRepository.findById(transactionId)).thenReturn(Optional.empty());
		assertThrows(PhysicalGoldTransactionNotFoundException.class,
				() -> physicalGoldTransactionServiceImpl.getPhysicalGoldTransactionById(transactionId));
	}

	@Test
	void testGetAllPhysicalGoldTransactionsByUserId_UserFound() throws UserNotFoundException {
		int userId = 1;
		PhysicalGoldTransaction t1 = new PhysicalGoldTransaction();
		PhysicalGoldTransaction t2 = new PhysicalGoldTransaction();
		List<PhysicalGoldTransaction> transactions = Arrays.asList(t1, t2);

		when(userService.getUserByUserId(userId)).thenReturn(new User());
		when(physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByUserId(userId))
				.thenReturn(transactions);
		List<PhysicalGoldTransaction> result = physicalGoldTransactionServiceImpl
				.getAllPhysicalGoldTransactionsByUserId(userId);

		assertAll(() -> assertEquals(2, result.size()), () -> assertEquals(t1, result.get(0)),
				() -> assertEquals(t2, result.get(1)));
	}

	@Test
	void testGetAllPhysicalGoldTransactionsByUserId_UserNotFound() throws UserNotFoundException {
		int userId = 1;

		when(userService.getUserByUserId(userId)).thenThrow(UserNotFoundException.class);
		assertThrows(UserNotFoundException.class,
				() -> physicalGoldTransactionServiceImpl.getAllPhysicalGoldTransactionsByUserId(userId));
	}

	@Test
	void testGetAllPhysicalGoldTransactionsByBranchId_BranchFound() throws VendorBranchNotFoundException {
		int branchId = 1;
		PhysicalGoldTransaction t1 = new PhysicalGoldTransaction();
		PhysicalGoldTransaction t2 = new PhysicalGoldTransaction();
		List<PhysicalGoldTransaction> transactions = Arrays.asList(t1, t2);

		when(vendorBranchService.getVendorBranchByBranchId(branchId)).thenReturn(new VendorBranch());
		when(physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByBranchId(branchId))
				.thenReturn(transactions);
		List<PhysicalGoldTransaction> result = physicalGoldTransactionServiceImpl
				.getAllPhysicalGoldTransactionsByBranchId(branchId);

		assertAll(() -> assertEquals(2, result.size()), () -> assertEquals(t1, result.get(0)),
				() -> assertEquals(t2, result.get(1)));
	}

	@Test
	void testGetAllPhysicalGoldTransactionsByBranchId_BranchNotFound() throws VendorBranchNotFoundException {
		int branchId = 1;

		when(vendorBranchService.getVendorBranchByBranchId(branchId)).thenThrow(VendorBranchNotFoundException.class);
		assertThrows(VendorBranchNotFoundException.class,
				() -> physicalGoldTransactionServiceImpl.getAllPhysicalGoldTransactionsByBranchId(branchId));
	}

	@Test
	void testGetAllPhysicalGoldTransactionsByDeliveryCity() {
		String city = "New York";
		PhysicalGoldTransaction t1 = new PhysicalGoldTransaction();
		PhysicalGoldTransaction t2 = new PhysicalGoldTransaction();
		List<PhysicalGoldTransaction> transactions = Arrays.asList(t1, t2);

		when(physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByCity(city)).thenReturn(transactions);
		List<PhysicalGoldTransaction> result = physicalGoldTransactionServiceImpl
				.getAllPhysicalGoldTransactionsByDeliveryCity(city);

		assertAll(() -> assertEquals(2, result.size()), () -> assertEquals(t1, result.get(0)),
				() -> assertEquals(t2, result.get(1)));
	}

	@Test
	void testGetAllPhysicalGoldTransactionsByDeliveryState() {
		String state = "NY";
		PhysicalGoldTransaction t1 = new PhysicalGoldTransaction();
		PhysicalGoldTransaction t2 = new PhysicalGoldTransaction();
		List<PhysicalGoldTransaction> transactions = Arrays.asList(t1, t2);

		when(physicalGoldTransactionRepository.findAllPhysicalGoldTransactionsByState(state)).thenReturn(transactions);
		List<PhysicalGoldTransaction> result = physicalGoldTransactionServiceImpl
				.getAllPhysicalGoldTransactionsByDeliveryState(state);

		assertAll(() -> assertEquals(2, result.size()), () -> assertEquals(t1, result.get(0)),
				() -> assertEquals(t2, result.get(1)));
	}

}
