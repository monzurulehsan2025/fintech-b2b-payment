package com.fintech.payments.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AuditLog {
    private String logId;
    private String eventType; // ACCESS, MODIFICATION, AUTH_FAILURE
    private String userId;
    private String ipAddress;
    private String action;
    private String complianceTag; // PCI-DSS, SOC2
    private String timestamp;
}
