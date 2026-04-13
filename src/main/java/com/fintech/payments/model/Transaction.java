package com.fintech.payments.model;

import lombok.Builder;
import lombok.Data;
import java.time.LocalDateTime;

@Data
@Builder
public class Transaction {
    private String transactionId;
    private String senderName;
    private String receiverName;
    private Double amount;
    private String currency;
    private String status;
    private String paymentMethod; // ACH, SWIFT, Card
    private String iso20022Code;
    private LocalDateTime timestamp;
}
