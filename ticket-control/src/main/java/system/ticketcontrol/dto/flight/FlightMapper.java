package system.ticketcontrol.dto.flight;

import org.springframework.stereotype.Component;
import system.ticketcontrol.model.Flight;

@Component
public class FlightMapper {
    public Flight toModel(FlightRequestDto dto) {
        Flight flight = new Flight();
        flight.setFlightId(dto.getFlightId());
        flight.setFrom(dto.getFrom());
        flight.setTo(dto.getTo());
        flight.setDepartureTime(dto.getDepartureTime());
        flight.setPrice(dto.getPrice());
        flight.setAvailableTickets(dto.getAvailableTickets());

        return flight;
    }

    public FlightResponseDto toDto(Flight flight) {
        FlightResponseDto dto = new FlightResponseDto();
        dto.setId(flight.getId());
        dto.setFlightId(flight.getFlightId());
        dto.setFrom(flight.getFrom());
        dto.setTo(flight.getTo());
        dto.setDepartureTime(flight.getDepartureTime());
        dto.setPrice(flight.getPrice());
        dto.setAvailableTickets(flight.getAvailableTickets());

        return dto;
    }
}
