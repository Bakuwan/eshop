package id.ac.ui.cs.advprog.eshop.repository;

import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentRepositoryTest {
    PaymentRepository paymentRepository;
    List<Payment> payments;
    Map<String, String> voucherData;
    Map<String, String> bankTransferData;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        payments = new ArrayList<>();

        voucherData = new HashMap<>();
        voucherData.put("voucherCode", "ESHOP1234ABC5678");

        bankTransferData = new HashMap<>();
        bankTransferData.put("bankName", "Bank ABC");
        bankTransferData.put("referenceCode", "REF123456");

        Payment payment1 = new Payment("pay-001", "Voucher", voucherData);
        Payment payment2 = new Payment("pay-002", "BankTransfer", bankTransferData);

        Map<String, String> invalidBankTransfer = new HashMap<>();
        invalidBankTransfer.put("bankName", "Bank XYZ");
        invalidBankTransfer.put("referenceCode", "");
        Payment payment3 = new Payment("pay-003", "BankTransfer", invalidBankTransfer);

        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);
    }

    @Test
    void testSaveCreate() {
        Payment payment = payments.get(2);
        Payment result = paymentRepository.save(payment);
        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(PaymentStatus.REJECTED.getValue(), findResult.getStatus());
    }

    @Test
    void testSaveUpdate() {
        Payment payment = payments.get(0);
        paymentRepository.save(payment);

        Payment newPayment = new Payment(
                payment.getId(),
                payment.getMethod(),
                payment.getPaymentData(),
                PaymentStatus.SUCCESS.getValue()
        );
        Payment result = paymentRepository.save(newPayment);
        Payment findResult = paymentRepository.findById(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getId(), findResult.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfFound() {
        for (Payment p : payments) {
            paymentRepository.save(p);
        }
        Payment findResult = paymentRepository.findById(payments.get(1).getId());
        assertEquals(payments.get(1).getId(), findResult.getId());
        assertEquals(PaymentStatus.SUCCESS.getValue(), findResult.getStatus());
    }

    @Test
    void testFindByIdIfNotFound() {
        for (Payment p : payments) {
            paymentRepository.save(p);
        }
        Payment findResult = paymentRepository.findById("non-existent-id");
        assertNull(findResult);
    }
}