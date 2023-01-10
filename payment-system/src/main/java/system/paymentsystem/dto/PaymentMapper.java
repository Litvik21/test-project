package system.paymentsystem.dto;

import system.paymentsystem.model.Payment;
import org.springframework.stereotype.Component;

@Component
public class PaymentMapper {

    public PaymentResponseDto toDto(Payment client) {
        PaymentResponseDto dto = new PaymentResponseDto();
        dto.setId(client.getId());
        dto.setFullName(client.getFullName());
        dto.setAmount(client.getAmount());
        if (client.getPaymentId() != null) {
            dto.setPaymentId(client.getPaymentId());
        }
        if (client.getPaymentStatus() != null) {
            dto.setPaymentStatus(client.getPaymentStatus());
        }
        return dto;
    }

}
