package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.NoSuchElementException;

@Service
public class PaymentServiceImpl implements PaymentService {
    @Autowired
    private PaymentRepository paymentRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Override
    public Payment addPayment(Order order, String method, Map<String, String> paymentData) {
        String paymentId = order.getId();
        Payment existingPayment = paymentRepository.findById(paymentId);
        if (existingPayment == null || existingPayment.getStatus().equals(PaymentStatus.REJECTED.getValue())) {
            Payment payment = new Payment(paymentId, method, PaymentStatus.SUCCESS.getValue(), paymentData);
            return paymentRepository.save(payment);
        }
        return null;
    }

    @Override
    public Payment setStatus(Payment payment, String status) {
        Payment existingPayment = paymentRepository.findById(payment.getId());
        if (existingPayment == null) {
            throw new NoSuchElementException("Payment not found");
        }

        existingPayment.setStatus(status);
        paymentRepository.save(existingPayment);

        Order order = orderRepository.findById(payment.getId());
        if (order != null) {
            String orderStatus = status.equals(PaymentStatus.REJECTED.getValue()) ? OrderStatus.FAILED.getValue() : OrderStatus.SUCCESS.getValue();
            order.setStatus(orderStatus);
            orderRepository.save(order);
        }

        return existingPayment;
    }

    @Override
    public Payment getPayment(String paymentId) {
        return paymentRepository.findById(paymentId);
    }

    @Override
    public List<Payment> getAllPayments() {
        return paymentRepository.findAll();
    }
}
