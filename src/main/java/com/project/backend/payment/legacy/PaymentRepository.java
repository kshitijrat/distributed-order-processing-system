package com.project.backend.payment.legacy;


import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

@Deprecated
public interface PaymentRepository extends JpaRepository<Payment, Long> {

    Optional<Payment> findByOrderId(Long orderId);
}

