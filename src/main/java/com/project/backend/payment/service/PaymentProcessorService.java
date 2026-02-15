package com.project.backend.payment.service;

import com.project.backend.order.entity.OrderStatus;
import com.project.backend.order.service.OrderService;
import com.project.backend.payment.entity.PaymentStatus;
import com.project.backend.payment.entity.PaymentTransaction;
import com.project.backend.payment.repo.PaymentTransactionRepository;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Service
public class PaymentProcessorService {

    @Autowired
    private  PaymentTransactionRepository transactionRepository;    
    @Autowired
    private OrderService orderService;

    private static final int MAX_RETRY = 3;

    public PaymentTransaction initiatePayment(Long orderId, Double amount) {

        PaymentTransaction txn = PaymentTransaction.builder()
                .orderId(orderId)
                .amount(amount)
                .status(PaymentStatus.INITIATED)
                .retryCount(0)
                .createdAt(LocalDateTime.now())
                .updatedAt(LocalDateTime.now())
                .build();

        txn = transactionRepository.save(txn);

        return processPayment(txn);
    }

    private PaymentTransaction processPayment(PaymentTransaction txn) {

        txn.setStatus(PaymentStatus.PROCESSING);
        txn.setUpdatedAt(LocalDateTime.now());
        transactionRepository.save(txn);

        // Fraud + failure simulation
        boolean fraudDetected = new Random().nextInt(10) < 2; // 20% fraud chance
        boolean paymentSuccess = new Random().nextBoolean();

        if (fraudDetected) {
            txn.setStatus(PaymentStatus.FAILED_FINAL);
            txn.setFailureReason("Fraud detected");
            orderService.updateOrderStatus(txn.getOrderId(), OrderStatus.FAILED);
        }
        else if (paymentSuccess) {
            txn.setStatus(PaymentStatus.SUCCESS);
            orderService.updateOrderStatus(txn.getOrderId(), OrderStatus.CONFIRMED);
        }
        else {
            return retryPayment(txn);
        }

        txn.setUpdatedAt(LocalDateTime.now());
        return transactionRepository.save(txn);
    }

    private PaymentTransaction retryPayment(PaymentTransaction txn) {

        int retry = txn.getRetryCount() + 1;

        if (retry >= MAX_RETRY) {
            txn.setStatus(PaymentStatus.FAILED_FINAL);
            txn.setFailureReason("Max retries reached");
            orderService.updateOrderStatus(txn.getOrderId(), OrderStatus.FAILED);
        } else {
            txn.setStatus(PaymentStatus.RETRY);
            txn.setRetryCount(retry);
            txn.setUpdatedAt(LocalDateTime.now());
            transactionRepository.save(txn);
            return processPayment(txn); // recursive retry
        }

        txn.setUpdatedAt(LocalDateTime.now());
        return transactionRepository.save(txn);
    }

    public PaymentTransaction getTransactionByOrderId(Long orderId) {
        return transactionRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Transaction not found"));
    }
}
