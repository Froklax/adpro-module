package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import java.util.Map;

public class PaymentByCash extends Payment {
    public PaymentByCash(String id, String method, Map<String, String> paymentData) {
        super(id, method, validatePaymentData(paymentData), paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        super.setPaymentData(paymentData);
        setStatus(validatePaymentData(paymentData));
    }

    private static String validatePaymentData(Map<String, String> paymentData) {
        if (paymentData == null || paymentData.get("address") == null || paymentData.get("address").isEmpty() ||
                paymentData.get("deliveryFee") == null || paymentData.get("deliveryFee").isEmpty()) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.SUCCESS.getValue();
    }
}
