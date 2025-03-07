package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

class PaymentTest {
    private Map<String, String> voucherCode;
    private Map<String, String> bankTransfer;

    @BeforeEach
    void setup() {
        voucherCode = new HashMap<>();
        voucherCode.put("voucherCode", "ESHOP1234ABC5678");

        bankTransfer = new HashMap<>();
        bankTransfer.put("bankName", "Bank ABC");
        bankTransfer.put("referenceCode", "REF123456");
    }

    @Test
    void testCreatePaymentDefaultStatus() {
        Payment payment = new Payment("ef16c464-e644-1234-a49c-7db5d9420ee0 ", "Voucher", voucherCode);
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentSuccessStatus() {
        Payment payment = new Payment("ef16c464-e644-5678-a49c-7db5d9420ee0 ", "BankTransfer", bankTransfer, "SUCCESS");
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("pay-003", "Voucher", voucherCode, "MEOW");
        });
    }

    @Test
    void testSetStatusToRejected() {
        Map<String, String> invalidBankTransfer = new HashMap<>();
        Payment payment = new Payment("ef16c464-e644-7654-a49c-7db5d9420ee0 ", "BankTransfer", invalidBankTransfer);
        assertEquals("REJECTED", payment.getStatus());
    }

    @Test
    void testSetStatusToInvalidStatus() {
        Payment payment = new Payment("ef16c464-e644-3245-a49c-7db5d9420ee0 ", "Voucher", voucherCode);
        assertThrows(IllegalArgumentException.class, () -> {
            payment.setStatus("MEOW");
        });
    }

    @Test
    void testInvalidPaymentCreationWithNullId() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment(null, "Voucher", voucherCode);
        });
    }

    @Test
    void testInvalidPaymentCreationWithEmptyMethod() {
        assertThrows(IllegalArgumentException.class, () -> {
            new Payment("pay-006", "", voucherCode);
        });
    }
}
