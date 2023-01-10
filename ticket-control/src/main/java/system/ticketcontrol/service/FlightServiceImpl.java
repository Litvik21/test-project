package system.ticketcontrol.service;

import java.util.List;
import org.springframework.stereotype.Service;
import system.ticketcontrol.model.Flight;
import system.ticketcontrol.repository.FlightRepository;

@Service
public class FlightServiceImpl implements FlightService {
    private final FlightRepository flightRepository;

    public FlightServiceImpl(FlightRepository flightRepository) {
        this.flightRepository = flightRepository;
    }

    @Override
    public List<Flight> getFlightsInfo() {
        return flightRepository.findAll();
    }

    @Override
    public Flight findByFlightId(Long flightId) {
        List<Flight> flights = flightRepository.findByFlightId(flightId);
        return flights.stream().findFirst().orElseThrow(()
                -> new RuntimeException("Can't get flight by flight ID: " + flightId));
    }

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }
}