package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
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
                    PaymentStatus.SUCCESS.getValue(),
                    paymentData);
        });
    }

    @Test
    void testCreatePaymentValidStatus() {
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment(
                "c23d45e6-f78g-901h-2345-i67j89k01lmn",
                PaymentMethod.BY_CASH_ON_DELIVERY.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                paymentData
        );
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testCreatePaymentInvalidStatus() {
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5000");
        assertThrows(IllegalArgumentException.class, () -> {
            Payment payment = new Payment(
                    "d34e56f7-g89h-012i-3456-j78k90l12mno",
                    PaymentMethod.BY_CASH_ON_DELIVERY.getValue(),
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
                PaymentMethod.BY_VOUCHER.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                paymentData
        );
        assertEquals(PaymentMethod.BY_VOUCHER.getValue(), payment.getMethod());
    }

    @Test
    void testSetStatusInvalid() {
        paymentData.put("address", "123 Main Street");
        paymentData.put("deliveryFee", "5000");
        Payment payment = new Payment(
                "f56g78h9-i01j-234k-5678-l90m12n34opq",
                PaymentMethod.BY_CASH_ON_DELIVERY.getValue(),
                PaymentStatus.SUCCESS.getValue(),
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
                PaymentMethod.BY_CASH_ON_DELIVERY.getValue(),
                PaymentStatus.SUCCESS.getValue(),
                paymentData
        );
        payment.setStatus(PaymentStatus.REJECTED.getValue());
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}