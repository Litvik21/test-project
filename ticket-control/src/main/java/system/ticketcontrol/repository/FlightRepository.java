package system.ticketcontrol.repository;

import system.ticketcontrol.model.Flight;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface FlightRepository extends JpaRepository<Flight, Long> {
    List<Flight> findByFlightId(Long flightId);
}