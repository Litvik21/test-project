package system.ticketcontrol.service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import system.ticketcontrol.dto.ClientResponseDto;
import system.ticketcontrol.model.Flight;
import system.ticketcontrol.model.Ticket;
import system.ticketcontrol.repository.TicketRepository;

@Service
public class TicketServiceImpl implements TicketService {
    private final TicketRepository ticketRepository;
    private final FlightService flightService;
    @Value("${paymentLink}")
    private String paymentLink;
    private final HttpClient httpClient;

    public TicketServiceImpl(TicketRepository ticketRepository, FlightService flightService,
                             HttpClient httpClient) {
        this.ticketRepository = ticketRepository;
        this.flightService = flightService;
        this.httpClient = httpClient;
    }

    @Override
    public List<Ticket> getAll() {
        return ticketRepository.findAll();
    }

    @Override
    public void save(Ticket ticket) {
        ticketRepository.save(ticket);
    }

    @Override
    public String buyTicket(String fullName, Long flightId) {
        Flight flight = flightService.findByFlightId(flightId);
        flight.setAvailableTickets(flight.getAvailableTickets() - 1);
        Ticket currentTicket = new Ticket();
        currentTicket.setFlightId(flightId);
        currentTicket.setFrom(flight.getFrom());
        currentTicket.setTo(flight.getTo());
        currentTicket.setDepartureTime(flight.getDepartureTime());
        currentTicket.setPrice(flight.getPrice());
        currentTicket.setTicketId(UUID.randomUUID().toString().substring(0, 6));
        currentTicket.setStatus(getStatusFromPaymentSystem(fullName));
        currentTicket.setFullName(fullName);
        save(currentTicket);

        return currentTicket.getTicketId();
    }

    @Override
    public Ticket getInfo(String ticketId) {
        return ticketRepository.findByTicketId(ticketId)
                .orElseThrow(() -> new RuntimeException("Can't find ticket by ticket ID: " + ticketId));
    }

    private String getStatusFromPaymentSystem(String fullName) {
        ClientResponseDto[] dtos = getFromApi();
        String status = Arrays.stream(dtos)
                .filter(d -> d.getFullName().equals(fullName))
                .map(ClientResponseDto::getPaymentStatus)
                .findFirst()
                .orElseThrow(() ->
                        new RuntimeException("Can't find status of payment with full name: " + fullName));
        return status;
    }

    private ClientResponseDto[] getFromApi() {
        ClientResponseDto[] dtos = new ClientResponseDto[0];
        try {
            URL obj = new URL(paymentLink);
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                dtos = httpClient.get(inputLine, ClientResponseDto[].class);
            }
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return dtos;
    }

    private String updateStatusForPayment() {
        StringBuilder newStatus = new StringBuilder();
        try {
            URL obj = new URL(paymentLink + "/update-status");
            HttpURLConnection connection = (HttpURLConnection) obj.openConnection();
            connection.setRequestMethod("GET");
            BufferedReader in = new BufferedReader(new InputStreamReader(connection.getInputStream()));
            String inputLine;

            while ((inputLine = in.readLine()) != null) {
                newStatus.append(inputLine);
            }
            in.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        return newStatus.toString();
    }

    @Scheduled(cron = "* 15 * * * *")
    private void checkForStatus() {
        List<Ticket> tickets = getAll();
        for (Ticket ticket : tickets) {
            if (ticket.getStatus().equals("FAIL")) {
                Flight flight = flightService.findByFlightId(ticket.getFlightId());
                flight.setAvailableTickets(flight.getAvailableTickets() + 1);
                ticketRepository.delete(ticket);
            }
            if (ticket.getStatus().equals("NEW")) {
                ticket.setStatus(updateStatusForPayment());
            }
        }
    }
}
