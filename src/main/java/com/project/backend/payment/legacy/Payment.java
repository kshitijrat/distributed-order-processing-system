package com.project.backend.payment.legacy;

import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDateTime;

@Deprecated
@Entity
@Table(name = "payments")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Payment {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long orderId;

    private Double amount;

    private String paymentMethod; // CARD, UPI, NETBANKING

    private String status; // SUCCESS / FAILED

    private LocalDateTime paymentTime;
}
