package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class PaymentRepository {
    private final List<Payment> paymentData = new ArrayList<>();

    public Payment save(Payment payment) {
        if (findById(payment.getId()) == null) {
            paymentData.add(payment);
            return payment;
        }
        return null;
    }

    public Payment update(Payment updatedPayment) {
        for (int i = 0; i < paymentData.size(); i++) {
            Payment existingPayment = paymentData.get(i);
            if (existingPayment.getId().equals(updatedPayment.getId())) {
                paymentData.set(i, updatedPayment);
                return updatedPayment;
            }
        }
        return null;
    }

    public Payment findById(String id) {
        for (Payment p : paymentData) {
            if (p.getId().equals(id)) {
                return p;
            }
        }
        return null;
    }

    public List<Payment> findAll() {
        return new ArrayList<>(paymentData);
    }
}
