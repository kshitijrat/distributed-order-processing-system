package com.project.backend.payment.entity;

public enum PaymentStatus {
    INITIATED,
    PROCESSING,
    SUCCESS,
    FAILED,
    RETRY,
    FAILED_FINAL
}
