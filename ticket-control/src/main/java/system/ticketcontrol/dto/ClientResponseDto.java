package system.ticketcontrol.dto;

import java.math.BigDecimal;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class ClientResponseDto {
    private Long id;
    private String fullName;
    private BigDecimal amount;
    private String paymentStatus;
    private String paymentId;
}
