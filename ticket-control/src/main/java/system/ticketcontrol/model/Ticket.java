package system.ticketcontrol.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import javax.persistence.*;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@EqualsAndHashCode(onlyExplicitlyIncluded = true)
@Entity
public class Ticket {
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
    @Column(name = "`ticket_id`")
    private String ticketId;
    @Column(name = "`status`")
    private String status;
    @Column(name = "`full-name`")
    private String fullName;
}
