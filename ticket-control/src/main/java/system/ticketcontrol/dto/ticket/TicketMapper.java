package system.ticketcontrol.dto.ticket;

import system.ticketcontrol.model.Ticket;
import org.springframework.stereotype.Component;

@Component
public class TicketMapper {

    public TicketResponseDto toDto(Ticket ticket) {
        TicketResponseDto dto = new TicketResponseDto();
        dto.setId(ticket.getId());
        dto.setFlightId(ticket.getFlightId());
        dto.setFrom(ticket.getFrom());
        dto.setTo(ticket.getTo());
        dto.setDepartureTime(ticket.getDepartureTime());
        dto.setPrice(ticket.getPrice());
        dto.setTicketId(ticket.getTicketId());
        dto.setStatus(ticket.getStatus());
        dto.setFullName(ticket.getFullName());

        return dto;
    }

}
