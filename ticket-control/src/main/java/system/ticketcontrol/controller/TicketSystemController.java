package system.ticketcontrol.controller;

import java.util.List;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import system.ticketcontrol.dto.flight.FlightMapper;
import system.ticketcontrol.dto.flight.FlightRequestDto;
import system.ticketcontrol.dto.flight.FlightResponseDto;
import system.ticketcontrol.dto.ticket.BuyingTicketRequestDto;
import system.ticketcontrol.dto.ticket.TicketMapper;
import system.ticketcontrol.dto.ticket.TicketResponseDto;
import system.ticketcontrol.model.Flight;
import system.ticketcontrol.model.Ticket;
import system.ticketcontrol.service.FlightService;
import system.ticketcontrol.service.TicketService;

@RestController
@RequestMapping("/tickets")
public class TicketSystemController {
    private final TicketService ticketService;
    private final FlightService flightService;
    private final TicketMapper ticketMapper;
    private final FlightMapper flightMapper;

    public TicketSystemController(TicketService ticketService, FlightService flightService,
                                  TicketMapper ticketMapper, FlightMapper flightMapper) {
        this.ticketService = ticketService;
        this.flightService = flightService;
        this.ticketMapper = ticketMapper;
        this.flightMapper = flightMapper;
    }

    @PostMapping
    @ApiOperation(value = "Creation of flights")
    public FlightResponseDto create(@RequestBody FlightRequestDto dto) {
        Flight flight = flightService.save(flightMapper.toModel(dto));
        return flightMapper.toDto(flight);
    }

    @GetMapping("/info")
    @ApiOperation(value = "Getting flights info")
    public List<FlightResponseDto> getFlightsInfo() {
        return flightService.getFlightsInfo().stream()
                .map(flightMapper::toDto)
                .toList();
    }

    @PostMapping("/buy")
    @ApiOperation(value = "Buying ticket of flight")
    public String buyTicket(@RequestBody BuyingTicketRequestDto dto) {
        return ticketService.buyTicket(dto.getFullName(), dto.getFlightId());
    }

    @GetMapping("/ticket-info")
    @ApiOperation(value = "Getting ticket info")
    public TicketResponseDto getInfo(
            @RequestParam @ApiParam(value = "id of ticket that you want get info")
                                         String ticketId) {
        Ticket ticket = ticketService.getInfo(ticketId);
        return ticketMapper.toDto(ticket);
    }
}
