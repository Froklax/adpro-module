package id.ac.ui.cs.advprog.eshop.model;

import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;

import java.util.Map;

public class PaymentByVoucher extends Payment {
    public PaymentByVoucher(String id, String method, Map<String, String> paymentData) {
        super(id, method, validateVoucher(paymentData), paymentData);
    }

    @Override
    public void setPaymentData(Map<String, String> paymentData) {
        if (paymentData == null || !paymentData.containsKey("voucherCode") || paymentData.get("voucherCode") == null) {
            throw new IllegalArgumentException();
        }

        super.setPaymentData(paymentData);
        setStatus(validateVoucher(paymentData));
    }

    private static String validateVoucher(Map<String, String> paymentData) {
        String voucherCode = paymentData.get("voucherCode");
        if (!isValidVoucher(voucherCode)) {
            return PaymentStatus.REJECTED.getValue();
        }
        return PaymentStatus.SUCCESS.getValue();
    }

    private static boolean isValidVoucher(String voucherCode) {
        if (voucherCode == null || voucherCode.length() != 16) {
            return false;
        }

        if (!voucherCode.startsWith("ESHOP")) {
            return false;
        }

        int digitCount = 0;
        for (char c : voucherCode.toCharArray()) {
            if (c >= '0' && c <= '9') {
                digitCount++;
            }
        }

        return digitCount == 8;
    }
}
