package com.project.backend.payment.legacy;

import com.project.backend.order.entity.OrderStatus;
import com.project.backend.order.service.OrderService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Random;

@Deprecated
@Service
public class PaymentService {

    @Autowired
    private  PaymentRepository paymentRepository;
    @Autowired
    private  OrderService orderService;

    public Payment processPayment(Payment payment) {

        // simulate payment success/failure
        boolean isSuccess = new Random().nextBoolean();

        payment.setPaymentTime(LocalDateTime.now());

        if (isSuccess) {
            payment.setStatus("SUCCESS");
            orderService.updateOrderStatus(payment.getOrderId(), OrderStatus.CONFIRMED);
        } else {
            payment.setStatus("FAILED");
            orderService.updateOrderStatus(payment.getOrderId(), OrderStatus.FAILED);
        }

        return paymentRepository.save(payment);
    }

    public Payment getPaymentByOrderId(Long orderId) {
        return paymentRepository.findByOrderId(orderId)
                .orElseThrow(() -> new RuntimeException("Payment not found"));
    }
}
