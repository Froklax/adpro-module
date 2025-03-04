package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;

class PaymentByCashTest {
    private Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testValidPaymentData() {
        this.paymentData.put("address", "Jl. Merdeka No. 123, Jakarta");
        this.paymentData.put("deliveryFee", "50000");
        PaymentByCash payment = new PaymentByCash("ORDER-007", PaymentMethod.BY_CASH_ON_DELIVERY.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidEmptyAddress() {
        this.paymentData.put("address", "");
        this.paymentData.put("deliveryFee", "50000");
        PaymentByCash payment = new PaymentByCash("ORDER-008", PaymentMethod.BY_CASH_ON_DELIVERY.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidNullAddress() {
        this.paymentData.put("address", null);
        this.paymentData.put("deliveryFee", "50000");
        PaymentByCash payment = new PaymentByCash("ORDER-009", PaymentMethod.BY_CASH_ON_DELIVERY.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidEmptyDeliveryFee() {
        this.paymentData.put("address", "Jl. Merdeka No. 123, Jakarta");
        this.paymentData.put("deliveryFee", "");
        PaymentByCash payment = new PaymentByCash("ORDER-010", PaymentMethod.BY_CASH_ON_DELIVERY.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidNullDeliveryFee() {
        this.paymentData.put("address", "Jl. Merdeka No. 123, Jakarta");
        this.paymentData.put("deliveryFee", null);
        PaymentByCash payment = new PaymentByCash("ORDER-011", PaymentMethod.BY_CASH_ON_DELIVERY.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidEmptyPaymentData() {
        PaymentByCash payment = new PaymentByCash("ORDER-012", PaymentMethod.BY_CASH_ON_DELIVERY.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}