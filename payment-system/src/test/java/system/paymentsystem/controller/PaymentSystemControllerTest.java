package system.paymentsystem.controller;

import java.math.BigDecimal;
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
import system.paymentsystem.dto.CreationPaymentRequestDto;
import system.paymentsystem.model.Payment;
import system.paymentsystem.model.Status;
import system.paymentsystem.service.PaymentService;

@ExtendWith(MockitoExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class PaymentSystemControllerTest {
    @MockBean
    private PaymentService clientService;

    @Autowired
    private MockMvc mockMvc;

    @BeforeEach
    void setUp() {
        RestAssuredMockMvc.mockMvc(mockMvc);
    }

    @Test
    void shouldCreatePaymentAndReturnPaymentId() {
        String fullName = "John Brown";
        BigDecimal amount = BigDecimal.valueOf(450);

        RestAssuredMockMvc.given()
                .contentType(ContentType.JSON)
                .body(new CreationPaymentRequestDto(fullName, amount))
                .when()
                .post("/payments/create")
                .then()
                .statusCode(200)
                .body(Matchers.blankString());
    }

    @Test
    void shouldGetStatus() {
        Mockito.when(clientService.findByPaymentId("45gDr6")).thenReturn(new Payment());
        Mockito.when(clientService.getPaymentStatus("45gDr6")).thenReturn(Status.DONE);

        RestAssuredMockMvc.given()
                .when()
                .get("/payments/status?paymentId=45gDr6")
                .then()
                .statusCode(200)
                .body(Matchers.equalTo("DONE"));
    }
}
