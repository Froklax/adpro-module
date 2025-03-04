package id.ac.ui.cs.advprog.eshop.enums;

import lombok.Getter;

@Getter
public enum PaymentMethod {
    BY_VOUCHER("BY_VOUCHER"),
    BY_CASH_ON_DELIVERY("BY_CASH_ON_DELIVERY");

    private final String value;

    PaymentMethod(String value) {
        this.value = value;
    }

    public static boolean contains(String param) {
        for (PaymentMethod paymentMethod : PaymentMethod.values()) {
            if (paymentMethod.name().equals(param)) {
                return true;
            }
        }
        return false;
    }
}