package id.ac.ui.cs.advprog.eshop.model;

import lombok.Getter;

import java.util.Arrays;
import java.util.Map;

@Getter
public class Payment {
    private String id;
    private String method;
    private String status;
    private Map<String, String> paymentData;

    public Payment(String id, String method, String status, Map<String, String> paymentData) {
        this.id = id;
        this.method = method;
        setStatus(status);
        setMethod(method);
    }

    public void setStatus(String status) {
        String[] statusList = {"SUCCESS", "REJECTED"};
        if (Arrays.stream(statusList).noneMatch(status::equals)) {
            throw new IllegalArgumentException("Invalid payment status: " + status);
        }
        this.status = status;
    }

    public void setMethod(String method) {
        String[] methodList = {"by-voucher", "by-cash-on-delivery"};
        if (java.util.Arrays.stream(methodList).noneMatch(item -> item.equals(method))) {
            throw new IllegalArgumentException("Invalid payment method: " + method);
        }
        this.method = method;
    }
}
