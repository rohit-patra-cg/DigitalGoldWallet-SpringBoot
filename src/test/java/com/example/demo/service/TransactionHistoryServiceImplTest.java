package com.example.demo.service;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.demo.entity.TransactionHistory;
import com.example.demo.enums.TransactionStatus;
import com.example.demo.enums.TxnHistoryTransactionType;
import com.example.demo.exception.TransactionHistoryNotFoundException;
import com.example.demo.repository.TransactionHistoryRepository;

import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.when;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class TransactionHistoryServiceImplTest {

	@Mock
	private TransactionHistoryRepository transactionHistoryRepository;

	@InjectMocks
	private TransactionHistoryServiceImpl transactionHistoryServiceImpl;

	@Test
	void testGetAllTransactionHistory() {
		TransactionHistory tx1 = new TransactionHistory();
		TransactionHistory tx2 = new TransactionHistory();
		List<TransactionHistory> transactionHistories = Arrays.asList(tx1, tx2);

		when(transactionHistoryRepository.findAll()).thenReturn(transactionHistories);

		List<TransactionHistory> result = transactionHistoryServiceImpl.getAllTransactionHistory();

		assertAll(() -> assertEquals(2, result.size()), () -> assertEquals(tx1, result.get(0)),
				() -> assertEquals(tx2, result.get(1)));
	}

	@Test
	void testGetTransactionHistoryByTransactionHistoryId_WhenTransactionHistoryExists()
			throws TransactionHistoryNotFoundException {
		TransactionHistory tx = new TransactionHistory();

		when(transactionHistoryRepository.findById(anyInt())).thenReturn(Optional.of(tx));
		TransactionHistory result = transactionHistoryServiceImpl.getTransactionHistoryByTransactionHistoryId(1);

		assertEquals(tx, result);
	}

	@Test
	void testGetTransactionHistoryByTransactionHistoryId_WhenTransactionHistoryDoesNotExists() {
		when(transactionHistoryRepository.findById(anyInt())).thenReturn(Optional.empty());
		assertThrows(TransactionHistoryNotFoundException.class,
				() -> transactionHistoryServiceImpl.getTransactionHistoryByTransactionHistoryId(1));
	}

	@Test
	void testGetTransactionHistoryByTransactionStatus_WhenTransactionStatusisSuccess() {
		TransactionStatus status = TransactionStatus.SUCCESS;
		TransactionHistory transaction = new TransactionHistory();
		transaction.setTransactionId(1);
		transaction.setTransactionStatus(status);
		List<TransactionHistory> expected = Arrays.asList(transaction);

		when(transactionHistoryRepository.findAllByTransactionStatus(status)).thenReturn(expected);

		List<TransactionHistory> actual = transactionHistoryServiceImpl
				.getTransactionHistoryByTransactionStatus(status);

		assertEquals(expected, actual);
	}

	@Test
	void testGetTransactionHistoryByTransactionStatus__WhenTransactionStatusisFailed() {
		TransactionStatus status = TransactionStatus.FAILED;
		TransactionHistory transaction = new TransactionHistory();
		transaction.setTransactionId(1);
		transaction.setTransactionStatus(status);
		List<TransactionHistory> expected = Arrays.asList(transaction);

		when(transactionHistoryRepository.findAllByTransactionStatus(status)).thenReturn(expected);

		List<TransactionHistory> actual = transactionHistoryServiceImpl
				.getTransactionHistoryByTransactionStatus(status);

		assertEquals(expected, actual);
	}

	@Test
	void testGetTransactionHistoryByTransactionType__WhenTransactionTypeisBuy() {
		TxnHistoryTransactionType type = TxnHistoryTransactionType.BUY;
		TransactionHistory transaction = new TransactionHistory();
		transaction.setTransactionId(1);
		transaction.setTransactionType(type);
		List<TransactionHistory> expected = Arrays.asList(transaction);

		when(transactionHistoryRepository.findAllByTransactionType(type)).thenReturn(expected);

		List<TransactionHistory> actual = transactionHistoryServiceImpl.getTransactionHistoryByTransactionType(type);

		assertEquals(expected, actual);
	}

	@Test
	void testGetTransactionHistoryByTransactionType__WhenTransactionTypeisSell() {
		TxnHistoryTransactionType type = TxnHistoryTransactionType.SELL;
		TransactionHistory transaction = new TransactionHistory();
		transaction.setTransactionId(1);
		transaction.setTransactionType(type);
		List<TransactionHistory> expected = Arrays.asList(transaction);

		when(transactionHistoryRepository.findAllByTransactionType(type)).thenReturn(expected);

		List<TransactionHistory> actual = transactionHistoryServiceImpl.getTransactionHistoryByTransactionType(type);

		assertEquals(expected, actual);
	}

}
