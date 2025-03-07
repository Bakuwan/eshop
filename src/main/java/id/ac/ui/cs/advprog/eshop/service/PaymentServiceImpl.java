package id.ac.ui.cs.advprog.eshop.service;

import enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;
import java.util.UUID;

public class PaymentServiceImpl implements PaymentService {

    private PaymentRepository paymentRepository;
    private Map<String, Order> paymentOrderMap = new HashMap<>();

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String paymentId = UUID.randomUUID().toString();
        Payment payment = new Payment(paymentId, method, paymentData);
        paymentRepository.save(payment);
        paymentOrderMap.put(payment.getId(), order);
        return payment;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        payment.setStatus(status);
        Order order = paymentOrderMap.get(payment.getId());
        if (order == null) {
            throw new NoSuchElementException();
        }
        if (PaymentStatus.SUCCESS.getValue().equals(status)) {
            order.setStatus(PaymentStatus.SUCCESS.getValue());
        } else if (PaymentStatus.REJECTED.getValue().equals(status)) {
            order.setStatus("FAILED");
        }
        paymentRepository.save(payment);
        return payment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        Payment payment = paymentRepository.findById(paymentId);
        if (payment == null) {
            throw new NoSuchElementException();
        }
        return payment;
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.getAllPayments();
    }
}
