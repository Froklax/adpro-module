package id.ac.ui.cs.advprog.eshop.repository;

import id.ac.ui.cs.advprog.eshop.model.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentRepositoryTest {
    private PaymentRepository paymentRepository;
    private Map<String, String> paymentData1;
    private Map<String, String> paymentData2;

    @BeforeEach
    void setUp() {
        paymentRepository = new PaymentRepository();
        paymentData1 = new HashMap<>();
        paymentData1.put("bankAccount", "123456789");

        paymentData2 = new HashMap<>();
        paymentData2.put("voucherCode", "DISCOUNT50");
    }

    @Test
    void testSaveAndFindById() {
        Payment payment = new Payment("id-001", "BY_CASH_ON_DELIVERY", "SUCCESS", paymentData1);
        paymentRepository.save(payment);

        Payment foundPayment = paymentRepository.findById("id-001");
        assertNotNull(foundPayment);
        assertEquals("id-001", foundPayment.getId());
        assertEquals("BY_CASH_ON_DELIVERY", foundPayment.getMethod());
        assertEquals("SUCCESS", foundPayment.getStatus());
    }

    @Test
    void testSaveAndUpdatePayment() {
        Payment initialPayment = new Payment("id-002", "BY_VOUCHER", "SUCCESS", paymentData2);
        paymentRepository.save(initialPayment);

        Payment updatedPayment = new Payment("id-002", "BY_VOUCHER", "REJECTED", paymentData2);
        paymentRepository.update(updatedPayment);

        Payment foundPayment = paymentRepository.findById("id-002");
        assertNotNull(foundPayment);
        assertEquals("REJECTED", foundPayment.getStatus());
    }

    @Test
    void testFindByIdNotFound() {
        Payment foundPayment = paymentRepository.findById("nonexistent-id");
        assertNull(foundPayment);
    }

    @Test
    void testFindAll() {
        Payment payment1 = new Payment("id-003", "BY_VOUCHER", "SUCCESS", paymentData1);
        Payment payment2 = new Payment("id-004", "BY_CASH_ON_DELIVERY", "REJECTED", paymentData2);

        paymentRepository.save(payment1);
        paymentRepository.save(payment2);

        List<Payment> allPayments = paymentRepository.findAll();
        assertEquals(2, allPayments.size());
    }
}
