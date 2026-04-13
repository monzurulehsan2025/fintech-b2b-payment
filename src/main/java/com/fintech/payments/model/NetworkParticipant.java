package com.fintech.payments.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class NetworkParticipant {
    private String participantId;
    private String businessName;
    private String country;
    private String networkStatus; // ACTIVE, VETTING, SUSPENDED
    private String primaryCurrency;
    private String registrationDate;
}
