package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;

public class PaymentByCash extends Payment {
    public PaymentByCash(String id, String method, Map<String, String> paymentData) {
        super(id, method, validatePaymentData(paymentData), paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
    }
}
