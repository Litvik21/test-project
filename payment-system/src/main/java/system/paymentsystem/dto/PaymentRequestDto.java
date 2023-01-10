package system.paymentsystem.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class PaymentRequestDto {
    private String fullName;
    private BigDecimal amount;
    private String paymentStatus;
    private String paymentId;
}
