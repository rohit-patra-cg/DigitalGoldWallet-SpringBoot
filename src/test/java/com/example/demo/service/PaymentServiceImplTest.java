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

import com.example.demo.entity.Payment;
import com.example.demo.enums.PaymentMethod;
import com.example.demo.enums.PaymentStatus;
import com.example.demo.exception.PaymentNotFoundException;
import com.example.demo.repository.PaymentRepository;

@SpringBootTest
@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

	@Mock
	private PaymentRepository paymentRepository;

	@InjectMocks
	private PaymentServiceImpl paymentServiceImpl;

	@InjectMocks
	private UserServiceImpl userServiceImpl;

	@Test
	void testGetAllpayments() {
		Payment p1 = new Payment();
		Payment p2 = new Payment();

		List<Payment> payments = Arrays.asList(p1, p2);
		when(paymentRepository.findAll()).thenReturn(payments);
		List<Payment> result = paymentServiceImpl.getAllPayments();
		assertAll(() -> assertEquals(2, result.size()), () -> assertEquals(p1, result.get(0)),
				() -> assertEquals(p2, result.get(1)));
	}

	@Test
	void testGetPaymentById_PaymentFound() throws PaymentNotFoundException {

		int paymentId = 1;
		Payment payment = new Payment();
		payment.setPaymentId(paymentId);
		when(paymentRepository.findById(paymentId)).thenReturn(Optional.of(payment));

		Payment foundPayment = paymentServiceImpl.getPaymentById(paymentId);
		assertEquals(paymentId, foundPayment.getPaymentId());
	}

	@Test
	void testGetAllPaymentsByPaymentStatus_WhenpaymentisSuccess() {

		PaymentStatus status = PaymentStatus.SUCCESS;
		Payment payment1 = new Payment();
		payment1.setPaymentId(1);
		payment1.setPaymentStatus(status);
		Payment payment2 = new Payment();
		payment2.setPaymentId(2);
		payment2.setPaymentStatus(status);
		List<Payment> payments = Arrays.asList(payment1, payment2);

		when(paymentRepository.findAllByPaymentStatus(status)).thenReturn(payments);
		List<Payment> result = paymentServiceImpl.getAllPaymentsByPaymentStatus(status);

		assertEquals(2, result.size());
		assertEquals(payment1.getPaymentId(), result.get(0).getPaymentId());
		assertEquals(payment2.getPaymentId(), result.get(1).getPaymentId());
	}

	@Test
	void testGetAllPaymentsByPaymentStatus_WhenPaymentisFailed() {

		PaymentStatus status = PaymentStatus.FAILED;
		Payment payment1 = new Payment();
		payment1.setPaymentId(1);
		payment1.setPaymentStatus(status);
		Payment payment2 = new Payment();
		payment2.setPaymentId(2);
		payment2.setPaymentStatus(status);
		List<Payment> payments = Arrays.asList(payment1, payment2);

		when(paymentRepository.findAllByPaymentStatus(status)).thenReturn(payments);
		List<Payment> result = paymentServiceImpl.getAllPaymentsByPaymentStatus(status);

		assertEquals(2, result.size());
		assertEquals(payment1.getPaymentId(), result.get(0).getPaymentId());
		assertEquals(payment2.getPaymentId(), result.get(1).getPaymentId());
	}

	@Test
	void testGetAllPaymentsByPaymentMethod_WhenPaymentMethodisCreditCard() {

		PaymentMethod method = PaymentMethod.CREDIT_CARD;
		Payment payment1 = new Payment();

		payment1.setPaymentId(1);
		payment1.setPaymentMethod(method);
		Payment payment2 = new Payment();
		payment2.setPaymentId(2);
		payment2.setPaymentMethod(method);
		List<Payment> payments = Arrays.asList(payment1, payment2);
		when(paymentRepository.findAllByPaymentMethod(method)).thenReturn(payments);

		List<Payment> result = paymentServiceImpl.getAllPaymentsByPaymentMethod(method);

		assertEquals(2, result.size());
		assertEquals(payment1.getPaymentId(), result.get(0).getPaymentId());
		assertEquals(payment2.getPaymentId(), result.get(1).getPaymentId());
	}

	@Test
	void testGetAllPaymentsByPaymentMethod_WhenPaymentMethodisDebitCard() {

		PaymentMethod method = PaymentMethod.DEBIT_CARD;
		Payment payment1 = new Payment();

		payment1.setPaymentId(1);
		payment1.setPaymentMethod(method);
		Payment payment2 = new Payment();
		payment2.setPaymentId(2);
		payment2.setPaymentMethod(method);
		List<Payment> payments = Arrays.asList(payment1, payment2);
		when(paymentRepository.findAllByPaymentMethod(method)).thenReturn(payments);

		List<Payment> result = paymentServiceImpl.getAllPaymentsByPaymentMethod(method);

		assertEquals(2, result.size());
		assertEquals(payment1.getPaymentId(), result.get(0).getPaymentId());
		assertEquals(payment2.getPaymentId(), result.get(1).getPaymentId());
	}

	@Test
	void testGetAllPaymentsByPaymentMethod_WhenPaymentMethodisGooglePay() {

		PaymentMethod method = PaymentMethod.GOOGLE_PAY;
		Payment payment1 = new Payment();

		payment1.setPaymentId(1);
		payment1.setPaymentMethod(method);
		Payment payment2 = new Payment();
		payment2.setPaymentId(2);
		payment2.setPaymentMethod(method);
		List<Payment> payments = Arrays.asList(payment1, payment2);
		when(paymentRepository.findAllByPaymentMethod(method)).thenReturn(payments);

		List<Payment> result = paymentServiceImpl.getAllPaymentsByPaymentMethod(method);

		assertEquals(2, result.size());
		assertEquals(payment1.getPaymentId(), result.get(0).getPaymentId());
		assertEquals(payment2.getPaymentId(), result.get(1).getPaymentId());
	}

}
