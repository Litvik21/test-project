package system.paymentsystem.controller;

import java.util.List;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.web.bind.annotation.*;
import system.paymentsystem.dto.*;
import system.paymentsystem.service.PaymentService;

@RestController
@RequestMapping("/payments")
public class PaymentSystemController {
    private final PaymentService paymentService;
    private final PaymentMapper mapper;

    public PaymentSystemController(PaymentService paymentService, PaymentMapper mapper) {
        this.paymentService = paymentService;
        this.mapper = mapper;
    }

    @PostMapping("/create")
    @ApiOperation(value = "Creation of payment")
    public String createPayment(@RequestBody CreationPaymentRequestDto dto) {
        return paymentService.createPayment(dto.getFullName(), dto.getAmount());
    }

    @GetMapping("/status")
    @ApiOperation(value = "Getting status of payment")
    public String getStatus(
            @RequestParam @ApiParam(value = "id of payment that you want get status")
            String paymentId) {
        return paymentService.getPaymentStatus(paymentId).name();
    }

    @GetMapping("/update-status")
    @ApiOperation(value = "Update status for payment")
    public String updateStatus() {
        return paymentService.updateStatus().name();
    }

    @GetMapping
    @ApiOperation(value = "Getting all payments")
    public List<PaymentResponseDto> findAll() {
        return paymentService.findAll().stream()
                .map(mapper::toDto)
                .toList();
    }
}
