package system.ticketcontrol.controller;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import io.restassured.http.ContentType;
import io.restassured.module.mockmvc.RestAssuredMockMvc;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import system.ticketcontrol.dto.flight.FlightRequestDto;
import system.ticketcontrol.dto.ticket.BuyingTicketRequestDto;
import system.ticketcontrol.model.Flight;
import system.ticketcontrol.model.Ticket;
import system.ticketcontrol.service.FlightService;
import system.ticketcontrol.service.TicketService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class TicketSystemControllerTest {
    @MockBean
    private TicketService ticketService;

    @MockBean
    private FlightService flightService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void shouldSaveFlight() {
        Flight flight = new Flight();
        flight.setFlightId(34L);
        flight.setFrom("Dnipro");
        flight.setTo("Lviv");
        flight.setDepartureTime(LocalDateTime.of(2023, 3, 12, 12, 25));
        flight.setPrice(BigDecimal.valueOf(250));
        flight.setAvailableTickets(22L);

        Mockito.when(flightService.save(flight)).thenReturn(new Flight(109L, 34L, "Dnipro",
                "Lviv", LocalDateTime.of(2023, 3, 12, 12, 25),
                BigDecimal.valueOf(250), 22L));

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new FlightRequestDto(flight.getFlightId(), flight.getFrom(), flight.getTo(),
                        flight.getDepartureTime(), flight.getPrice(), flight.getAvailableTickets()))
                .when()
                .post("/tickets")
                .then()
                .statusCode(200)
                .body("id", Matchers.equalTo(109))
                .body("flightId", Matchers.equalTo(34))
                .body("from", Matchers.equalTo("Dnipro"))
                .body("to", Matchers.equalTo("Lviv"));
    }

    @Test
    void shouldBuyATicket() {
        BuyingTicketRequestDto dto =
                new BuyingTicketRequestDto("John Brown", 34L);
        Mockito.when(ticketService.buyTicket(dto.getFullName(), dto.getFlightId()))
                .thenReturn("r094Lfk");

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(dto)
                .when()
                .post("/tickets/buy")
                .then()
                .statusCode(200)
                .body(Matchers.hasToString("r094Lfk"));
    }

    @Test
    void shouldReturnInfoOfTicket() {
        Ticket ticket = new Ticket();
        ticket.setFlightId(34L);
        ticket.setFrom("Dnipro");
        ticket.setTo("Lviv");
        ticket.setDepartureTime(LocalDateTime.of(2023, 3, 12, 12, 25));
        ticket.setPrice(BigDecimal.valueOf(250));
        ticket.setTicketId("r094Lfk");
        ticket.setStatus("DONE");
        ticket.setFullName("John Brown");

        Mockito.when(ticketService.getInfo("r094Lfk")).thenReturn(ticket);

        RestAssuredMockMvc.given()
                .when()
                .get("/tickets/ticket-info?ticketId=r094Lfk")
                .then()
                .statusCode(200)
                .body("from", Matchers.equalTo(ticket.getFrom()))
                .body("to", Matchers.equalTo(ticket.getTo()))
                .body("ticketId", Matchers.equalTo(ticket.getTicketId()))
                .body("status", Matchers.equalTo(ticket.getStatus()))
                .body("fullName", Matchers.equalTo(ticket.getFullName()));
    }
}
