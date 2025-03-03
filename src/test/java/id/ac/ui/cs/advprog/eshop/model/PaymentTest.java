package id.ac.ui.cs.advprog.eshop.model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testCreatePaymentInvalidMethod() {
        paymentData.put("ewallet", "082233445566");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "a12f34b5-c67d-890e-1234-f56g78h90ijk",
                    "by-ewallet",
                    "SUCCESS",
                    paymentData);
        });
    }

    @Test
    void testCreatePaymentValidStatus() {
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment(
                "c23d45e6-f78g-901h-2345-i67j89k01lmn",
                "by-cash-on-delivery",
                "SUCCESS",
                paymentData
        );
        assertEquals("SUCCESS", payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5000");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "d34e56f7-g89h-012i-3456-j78k90l12mno",
                    "by-cash-on-delivery",
                    "INVALID_STATUS",
                    paymentData
            );
        });
    }

    @Test
    void testCreatePaymentValidMethod() {
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment(
                "e45f67g8-h90i-123j-4567-k89l01m23nop",
                "by-voucher",
                "SUCCESS",
                paymentData
        );
        assertEquals("by-voucher", payment.getMethod());
    }

    @Test
    void testSetStatusInvalid() {
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment(
                "f56g78h9-i01j-234k-5678-l90m12n34opq",
                "by-cash-on-delivery",
                "SUCCESS",
                paymentData
        );
        assertThrows(IllegalArgumentException.class, () -> payment.setStatus("UNKNOWN_STATUS"));
    }

    @Test
    void testSetStatusToRejected() {
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment(
                "g67h89i0-j12k-345l-6789-m01n23o45pqr",
                "by-cash-on-delivery",
                "SUCCESS",
                paymentData
        );
        payment.setStatus("REJECTED");
        assertEquals("REJECTED", payment.getStatus());
    }
}
