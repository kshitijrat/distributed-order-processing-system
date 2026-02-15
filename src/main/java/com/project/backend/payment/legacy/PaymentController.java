package com.project.backend.payment.legacy;

import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@Deprecated
@RestController
@RequestMapping("/api/payments")
@RequiredArgsConstructor
public class PaymentController {

    private final PaymentService paymentService;

    // Process Payment for an Order
    @PostMapping("/process")
    public Payment processPayment(@RequestBody Payment payment) {
        return paymentService.processPayment(payment);
    }

    // Get Payment by Order ID
    @GetMapping("/{orderId}")
    public Payment getPayment(@PathVariable Long orderId) {
        return paymentService.getPaymentByOrderId(orderId);
    }
}

