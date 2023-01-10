package system.paymentsystem.dto;

import java.math.BigDecimal;
import system.paymentsystem.model.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class PaymentResponseDto {
    private Long id;
    private String fullName;
    private BigDecimal amount;
    private Status paymentStatus;
    private String paymentId;
}
