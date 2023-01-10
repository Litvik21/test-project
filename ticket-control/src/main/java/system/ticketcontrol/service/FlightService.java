package system.ticketcontrol.service;

import java.util.List;
import system.ticketcontrol.model.Flight;

public interface FlightService {
    List<Flight> getFlightsInfo();

    Flight findByFlightId(Long flightId);

    Flight save(Flight flight);
}