package system.paymentsystem.model;

import java.math.BigDecimal;
import javax.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Entity
public class Payment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String paymentId;
    private String fullName;
    private BigDecimal amount;
    @Enumerated(value = EnumType.STRING)
    private Status paymentStatus;
}
