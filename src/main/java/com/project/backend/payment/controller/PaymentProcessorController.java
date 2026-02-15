package com.project.backend.payment.controller;

import com.project.backend.payment.entity.PaymentTransaction;
import com.project.backend.payment.service.PaymentProcessorService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/payment-processor")
@RequiredArgsConstructor
public class PaymentProcessorController {

    private final PaymentProcessorService paymentProcessorService;

    // Initiate payment for order
    @PostMapping("/pay")
    public PaymentTransaction pay(@RequestParam Long orderId,
                                  @RequestParam Double amount) {
        return paymentProcessorService.initiatePayment(orderId, amount);
    }

    // Get transaction by orderId
    @GetMapping("/{orderId}")
    public PaymentTransaction getTransaction(@PathVariable Long orderId) {
        return paymentProcessorService.getTransactionByOrderId(orderId);
    }
}
