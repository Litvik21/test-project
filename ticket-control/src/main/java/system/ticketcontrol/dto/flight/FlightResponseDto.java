package system.ticketcontrol.dto.flight;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
public class FlightResponseDto {
    private Long id;
    private Long flightId;
    private String from;
    private String to;
    private LocalDateTime departureTime;
    private BigDecimal price;
    private Long availableTickets;
}
