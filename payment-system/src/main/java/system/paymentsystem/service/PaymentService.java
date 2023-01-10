package system.paymentsystem.service;

import java.math.BigDecimal;
import java.util.List;
import system.paymentsystem.model.Payment;
import system.paymentsystem.model.Status;

public interface PaymentService {
    void save(Payment client);

    Payment findByPaymentId(String paymentId);

    String createPayment(String fullName, BigDecimal amount);

    Status getPaymentStatus(String uniqueKeyPayment);

    Status updateStatus();

    List<Payment> findAll();
}
