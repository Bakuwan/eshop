package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class PaymentServiceImplTest {

    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    Order order;
    List<Product> products;
    Map<String, String> voucherData;
    Map<String, String> bankTransferData;

    @BeforeEach
    void setUp() {
        products = new ArrayList<>();
        Product product = new Product();
        product.setProductId("prod-001");
        product.setProductName("Test Product");
        product.setProductQuantity(1);
        products.add(product);

        order = new Order("order-001", products, System.currentTimeMillis(), "Test Author");

        voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");

        bankTransferData = new HashMap<>();
        bankTransferData.put("bankName", "Bank ABC");
        bankTransferData.put("referenceCode", "REF123456");
    }

    @Test
    void testAddPaymentVoucherValid() {
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Payment payment = paymentService.addPayment(order, "Voucher", voucherData);
        assertEquals("SUCCESS", payment.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testAddPaymentBankTransferInvalid() {
        Map<String, String> invalidBankTransfer = new HashMap<>();
        invalidBankTransfer.put("bankName", "Bank ABC");
        invalidBankTransfer.put("referenceCode", ""); // invalid
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));

        Payment payment = paymentService.addPayment(order, "BankTransfer", invalidBankTransfer);
        assertEquals("REJECTED", payment.getStatus());
        verify(paymentRepository, times(1)).save(payment);
    }

    @Test
    void testSetStatusToSuccessUpdatesOrder() {
        Map<String, String> invalidBankTransfer = new HashMap<>();
        invalidBankTransfer.put("bankName", "Bank ABC");
        invalidBankTransfer.put("referenceCode", "");
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Payment payment = paymentService.addPayment(order, "BankTransfer", invalidBankTransfer);
        assertEquals("REJECTED", payment.getStatus());
        paymentService.setStatus(payment, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
        assertEquals("SUCCESS", order.getStatus());
        verify(paymentRepository, times(2)).save(payment);
    }

    @Test
    void testSetStatusToRejectedUpdatesOrder() {
        when(paymentRepository.save(any(Payment.class))).thenAnswer(invocation -> invocation.getArgument(0));
        Payment payment = paymentService.addPayment(order, "Voucher", voucherData);
        assertEquals("SUCCESS", payment.getStatus());
        paymentService.setStatus(payment, "REJECTED");
        assertEquals("REJECTED", payment.getStatus());
        assertEquals("FAILED", order.getStatus());
        verify(paymentRepository, times(2)).save(payment);
    }


    @Test
    void testGetPayment() {
        when(paymentRepository.findById("pay-001")).thenReturn(new Payment("pay-001", "Voucher", voucherData));
        Payment found = paymentService.getPayment("pay-001");
        assertNotNull(found);
        assertEquals("pay-001", found.getId());
    }

    @Test
    void testGetPaymentNotFound() {
        when(paymentRepository.findById("non-existent")).thenReturn(null);
        assertThrows(NoSuchElementException.class, () -> paymentService.getPayment("non-existent"));
    }

    @Test
    void testGetAllPayments() {
        List<Payment> allPayments = new ArrayList<>();
        Payment payment1 = new Payment("pay-001", "Voucher", voucherData);
        Payment payment2 = new Payment("pay-002", "BankTransfer", bankTransferData);
        allPayments.add(payment1);
        allPayments.add(payment2);
        when(paymentRepository.getAllPayments()).thenReturn(allPayments);

        List<Payment> result = paymentService.getAllPayments();
        assertNotNull(result);
        assertEquals(2, result.size());
        verify(paymentRepository, times(1)).getAllPayments();
    }
}
