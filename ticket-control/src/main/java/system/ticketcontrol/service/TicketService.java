package system.ticketcontrol.service;

import java.util.List;
import system.ticketcontrol.model.Ticket;

public interface TicketService {
    void save(Ticket ticket);

    List<Ticket> getAll();

    String buyTicket(String fullName, Long flightId);

    Ticket getInfo(String ticketId);
}