package id.ac.ui.cs.advprog.eshop.service;

import id.ac.ui.cs.advprog.eshop.enums.OrderStatus;
import id.ac.ui.cs.advprog.eshop.enums.PaymentMethod;
import id.ac.ui.cs.advprog.eshop.enums.PaymentStatus;
import id.ac.ui.cs.advprog.eshop.model.Order;
import id.ac.ui.cs.advprog.eshop.model.Payment;
import id.ac.ui.cs.advprog.eshop.model.Product;
import id.ac.ui.cs.advprog.eshop.repository.OrderRepository;
import id.ac.ui.cs.advprog.eshop.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PaymentServiceTest {
    @InjectMocks
    PaymentServiceImpl paymentService;

    @Mock
    PaymentRepository paymentRepository;

    @Mock
    OrderRepository orderRepository;

    List<Payment> payments;
    Order order;

    @BeforeEach
    void setUp() {
        this.payments = new ArrayList<>();
        List<Product> products = new ArrayList<>();

        Product product1 = new Product();
        product1.setProductId("P001-ABCD-1234");
        product1.setProductName("Sabun Mandi");
        product1.setProductQuantity(2);
        products.add(product1);

        this.order = new Order("O001-XYZ-999", products, 1709570000L, "AMBATUKAM");

        Map<String, String> validVoucherData = Map.of("voucherCode", "DISKON2024");
        Map<String, String> invalidVoucherData = Map.of("voucherCode", "INVALID999");
        Map<String, String> validCashData = Map.of("address", "Jl. Melati No. 20");

        Payment payment1 = new Payment("PAY-VCH-111", PaymentMethod.BY_VOUCHER.getValue(), PaymentStatus.SUCCESS.getValue(), validVoucherData);
        Payment payment2 = new Payment("PAY-VCH-222", PaymentMethod.BY_VOUCHER.getValue(), PaymentStatus.REJECTED.getValue(), invalidVoucherData);
        Payment payment3 = new Payment("PAY-COD-333", PaymentMethod.BY_CASH_ON_DELIVERY.getValue(), PaymentStatus.SUCCESS.getValue(), validCashData);
        payments.add(payment1);
        payments.add(payment2);
        payments.add(payment3);
    }

    @Test
    void testAddPaymentByVoucher() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
    }

    @Test
    void testAddPaymentByCashOnDelivery() {
        Payment payment = payments.get(2);
        doReturn(payment).when(paymentRepository).save(any(Payment.class));

        Payment result = paymentService.addPayment(order, payment.getMethod(), payment.getPaymentData());

        verify(paymentRepository, times(1)).save(any(Payment.class));
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
    }

    @Test
    void testUpdateVoucherPaymentStatusToSuccess() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).save(order);

        Payment result = paymentService.setStatus(payment, PaymentStatus.SUCCESS.getValue());

        assertEquals(PaymentStatus.SUCCESS.getValue(), result.getStatus());
        assertEquals(OrderStatus.SUCCESS.getValue(), order.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateCashOnDeliveryPaymentStatusToRejected() {
        Payment payment = payments.get(2);
        doReturn(payment).when(paymentRepository).findById(payment.getId());
        doReturn(payment).when(paymentRepository).save(any(Payment.class));
        doReturn(order).when(orderRepository).findById(payment.getId());
        doReturn(order).when(orderRepository).save(order);

        Payment result = paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue());

        assertEquals(PaymentStatus.REJECTED.getValue(), result.getStatus());
        assertEquals(OrderStatus.FAILED.getValue(), order.getStatus());
        verify(paymentRepository, times(1)).save(any(Payment.class));
        verify(orderRepository, times(1)).save(any(Order.class));
    }

    @Test
    void testUpdateInvalidStatus() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        assertThrows(IllegalArgumentException.class, () -> paymentService.setStatus(payment, "INVALID_STATUS"));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testUpdateNonexistentPayment() {
        Payment payment = new Payment("PAY-NOTFOUND", PaymentMethod.BY_CASH_ON_DELIVERY.getValue(), PaymentStatus.REJECTED.getValue(), new HashMap<>());
        doReturn(null).when(paymentRepository).findById("PAY-NOTFOUND");

        assertThrows(NoSuchElementException.class, () -> paymentService.setStatus(payment, PaymentStatus.REJECTED.getValue()));

        verify(paymentRepository, times(0)).save(any(Payment.class));
    }

    @Test
    void testGetPaymentById() {
        Payment payment = payments.getFirst();
        doReturn(payment).when(paymentRepository).findById(payment.getId());

        Payment result = paymentService.getPayment(payment.getId());
        assertEquals(payment.getId(), result.getId());
        assertEquals(payment.getMethod(), result.getMethod());
        assertEquals(payment.getStatus(), result.getStatus());
        assertEquals(payment.getPaymentData(), result.getPaymentData());
    }

    @Test
    void testGetPaymentByNonexistentId() {
        doReturn(null).when(paymentRepository).findById("UNKNOWN_ID");
        assertNull(paymentService.getPayment("UNKNOWN_ID"));
    }

    @Test
    void testGetAllPayments() {
        doReturn(payments).when(paymentRepository).findAll();

        List<Payment> results = paymentService.getAllPayments();
        assertEquals(payments.size(), results.size());
    }
}
