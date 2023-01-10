package system.paymentsystem.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.UUID;
import org.springframework.stereotype.Service;
import system.paymentsystem.model.Payment;
import system.paymentsystem.model.Status;
import system.paymentsystem.repository.PaymentRepository;

@Service
public class PaymentServiceImpl implements PaymentService {
    private final PaymentRepository paymentRepository;

    public PaymentServiceImpl(PaymentRepository paymentRepository) {
        this.paymentRepository = paymentRepository;
    }

    @Override
    public void save(Payment payment) {
        paymentRepository.save(payment);
    }

    @Override
    public Payment findByPaymentId(String paymentId) {
        return paymentRepository.findByPaymentId(paymentId);
    }

    @Override
    public String createPayment(String fullName, BigDecimal amount) {
        Payment payment = new Payment();
        payment.setFullName(fullName);
        payment.setAmount(amount);
        payment.setPaymentId(UUID.randomUUID().toString().substring(0, 6));
        save(payment);

        return payment.getPaymentId();
    }

    @Override
    public Status getPaymentStatus(String paymentId) {
        List<Status> enumValues = Arrays.asList(Status.values());
        Random rand = new Random();
        Payment byPaymentId = findByPaymentId(paymentId);
        byPaymentId.setPaymentStatus(enumValues.get(rand.nextInt(enumValues.size())));
        save(byPaymentId);

        return byPaymentId.getPaymentStatus();
    }

    @Override
    public Status updateStatus() {
        List<Status> enumValues = Arrays.asList(Status.values());
        Random rand = new Random();
        return enumValues.get(rand.nextInt(enumValues.size()));
    }

    @Override
    public List<Payment> findAll() {
        return paymentRepository.findAll();
    }
}
