package system.ticketcontrol.model;

import javax.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

import lombok.*;

@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Getter
@Setter
@Entity
public class Flight {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @EqualsAndHashCode.Include
    private Long id;
    @Column(name = "`flight_id`")
    private Long flightId;
    @Column(name = "`where_from`")
    private String from;
    @Column(name = "`where_to`")
    private String to;
    @Column(name = "`departure_time`")
    private LocalDateTime departureTime;
    @Column(name = "`price`")
    private BigDecimal price;
    @Column(name = "`available-tickets`")
    private Long availableTickets;
}
