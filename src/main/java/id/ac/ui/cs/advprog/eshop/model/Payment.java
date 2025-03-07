package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, Map<String, String> paymentData) {
        if (id == null || id.trim().isEmpty()) {
            throw new IllegalArgumentException();
        } else if (method == null || method.trim().isEmpty()) {
            throw new IllegalArgumentException();
        }

        this.id = id;
        this.method = method;
        this.paymentData = paymentData != null ? paymentData : new HashMap<>();

        validatePaymentData();
    }

    public Payment(String id, String method, Map<String, String> paymentData, String status) {
        this(id, method, paymentData);

        String[] validStatuses = {"PENDING", "SUCCESS", "REJECTED"};
        if (Arrays.stream(validStatuses).noneMatch(item -> item.equals(status))) {
            throw new IllegalArgumentException();
        } else {
            this.status = status;
        }
        validatePaymentData();
    }


    public void setStatus(String status) {
        String[] validStatuses = {"PENDING", "SUCCESS", "REJECTED"};
        if (status == null || status.trim().isEmpty()
                || Arrays.stream(validStatuses).noneMatch(item -> item.equals(status))) {
            throw new IllegalArgumentException();
        }
        this.status = status;
    }

    private void validatePaymentData() {
        if (method == null) {
            return;
        }
        if (method.equalsIgnoreCase("Voucher")) {
            String voucher = paymentData.get("voucherCode");
            if (voucher == null) {
                this.status = "REJECTED";
            } else {
                boolean valid = voucher.length() == 16 &&
                        voucher.startsWith("ESHOP") &&
                        countDigits(voucher) == 8;
                this.status = valid ? "SUCCESS" : "REJECTED";
            }
        } else if (method.equalsIgnoreCase("BankTransfer")) {
            String bankName = paymentData.get("bankName");
            String referenceCode = paymentData.get("referenceCode");
            if (bankName == null || bankName.trim().isEmpty() ||
                    referenceCode == null || referenceCode.trim().isEmpty()) {
                this.status = "REJECTED";
            } else {
                this.status = "SUCCESS";
            }
        }
    }

    private int countDigits(String input) {
        int count = 0;
        for (char c : input.toCharArray()) {
            if (Character.isDigit(c)) {
                count++;
            }
        }
        return count;
    }
}
