package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class PaymentByVoucher extends Payment {
    public PaymentByVoucher(String id, String method, Map<String, String> paymentData) {
        super(id, method, validateVoucher(paymentData), paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
    }

    private static String validateVoucher(Map<String, String> paymentData) {
    }

    private static boolean isValidVoucher(String voucherCode) {
    }
}
