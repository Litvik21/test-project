package system.ticketcontrol.dto.flight;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@AllArgsConstructor
@Setter
@Getter
public class FlightRequestDto {
    private Long flightId;
    private String from;
    private String to;
    private LocalDateTime departureTime;
    private BigDecimal price;
    private Long availableTickets;
}
