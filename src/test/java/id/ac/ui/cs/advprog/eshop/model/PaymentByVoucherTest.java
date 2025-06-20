package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.HashMap;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.*;

public class PaymentByVoucherTest {
    Map<String, String> paymentData;

    @BeforeEach
    void setUp() {
        this.paymentData = new HashMap<>();
    }

    @Test
    void testEmptyPaymentData() {
        PaymentByVoucher payment = new PaymentByVoucher("ORDER-001", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(this.paymentData));
    }

    @Test
    void testValidVoucherCode() {
        this.paymentData.put("voucherCode", "ESHOP1234ABD5678");
        PaymentByVoucher payment = new PaymentByVoucher("ORDER-001", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidVoucherCodeShortLength() {
        this.paymentData.put("voucherCode", "ESHOP1234XYZ567");
        PaymentByVoucher payment = new PaymentByVoucher("ORDER-001", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidVoucherCodeNoESHOPPrefix() {
        this.paymentData.put("voucherCode", "STORE1234ABCD5678");
        PaymentByVoucher payment = new PaymentByVoucher("ORDER-001", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testInvalidVoucherCodeWithout8Digits() {
        this.paymentData.put("voucherCode", "ESHOPABCDEFGHJKLM");
        PaymentByVoucher payment = new PaymentByVoucher("ORDER-001", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testValidVoucherWithNumbersAtDifferentPositions() {
        this.paymentData.put("voucherCode", "ESHOP99AB12C5678");
        PaymentByVoucher payment = new PaymentByVoucher("ORDER-002", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.SUCCESS.getValue(), payment.getStatus());
    }

    @Test
    void testVoucherWithExtraCharacters() {
        this.paymentData.put("voucherCode", "ESHOP1234ABCD56789X");
        PaymentByVoucher payment = new PaymentByVoucher("ORDER-003", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testVoucherWithSpecialCharacters() {
        this.paymentData.put("voucherCode", "ESHOP12@4ABCD5678");
        PaymentByVoucher payment = new PaymentByVoucher("ORDER-004", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }

    @Test
    void testNullVoucherCode() {
        this.paymentData.put("voucherCode", null);
        PaymentByVoucher payment = new PaymentByVoucher("ORDER-005", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        assertThrows(IllegalArgumentException.class, () -> payment.setPaymentData(this.paymentData));
    }

    @Test
    void testVoucherWithOnlyNumbers() {
        this.paymentData.put("voucherCode", "ESHOP12345678901");
        PaymentByVoucher payment = new PaymentByVoucher("ORDER-006", PaymentMethod.BY_VOUCHER.getValue(), this.paymentData);
        payment.setPaymentData(this.paymentData);
        assertEquals(PaymentStatus.REJECTED.getValue(), payment.getStatus());
    }
}
