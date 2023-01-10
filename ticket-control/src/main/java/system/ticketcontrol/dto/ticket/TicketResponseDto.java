package system.ticketcontrol.dto.ticket;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class TicketResponseDto {
    private Long id;
    private Long flightId;
    private String from;
    private String to;
    private LocalDateTime departureTime;
    private BigDecimal price;
    private String ticketId;
    private String status;
    private String fullName;
}
