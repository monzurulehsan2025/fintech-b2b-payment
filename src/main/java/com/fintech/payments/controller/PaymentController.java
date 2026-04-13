package com.fintech.payments.controller;

import com.fintech.payments.model.*;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/v1")
public class PaymentController {

    /**
     * 1. GET /api/v1/payments
     * Returns a list of recent B2B transactions.
     */
    @GetMapping("/payments")
    public List<Transaction> getRecentPayments() {
        return Arrays.asList(
            Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .senderName("Global Logistics Inc.")
                .receiverName("Pacific Shipping Co.")
                .amount(1250000.00)
                .currency("USD")
                .status("COMPLETED")
                .paymentMethod("SWIFT")
                .iso20022Code("pacs.008.001.07")
                .timestamp(LocalDateTime.now().minusHours(2))
                .build(),
            Transaction.builder()
                .transactionId(UUID.randomUUID().toString())
                .senderName("TechVentures LLC")
                .receiverName("Cloud Infrastructure Corp")
                .amount(45000.50)
                .currency("EUR")
                .status("PENDING")
                .paymentMethod("ACH")
                .iso20022Code("pain.001.001.03")
                .timestamp(LocalDateTime.now().minusMinutes(45))
                .build()
        );
    }

    /**
     * 2. POST /api/v1/payments/initiate
     * Initiates a new payment workflow.
     */
    @PostMapping("/payments/initiate")
    public WorkflowStatus initiatePayment(@RequestBody Transaction request) {
        return WorkflowStatus.builder()
            .workflowId("wf-" + UUID.randomUUID().toString().substring(0, 8))
            .transactionId(UUID.randomUUID().toString())
            .currentState("Initiated")
            .nextState("KYC_Verification")
            .stepsCompleted(Arrays.asList("Validation", "Duplicate_Check"))
            .executionTime("145ms")
            .build();
    }

    /**
     * 3. GET /api/v1/accounts/balance
     * Returns balances for B2B settlement accounts.
     */
    @GetMapping("/accounts/balance")
    public List<AccountBalance> getAccountBalances() {
        return Arrays.asList(
            AccountBalance.builder()
                .accountId("ACC-88219")
                .accountType("Main Settlement")
                .balance(42500000.00)
                .currency("USD")
                .lastUpdated(LocalDateTime.now().toString())
                .build(),
            AccountBalance.builder()
                .accountId("ACC-11023")
                .accountType("Reserve Fund")
                .balance(5000000.00)
                .currency("USD")
                .lastUpdated(LocalDateTime.now().minusHours(1).toString())
                .build()
        );
    }

    /**
     * 4. GET /api/v1/workflows/{workflowId}
     * Checks the status of a specific payment workflow (AWS Step Functions mock).
     */
    @GetMapping("/workflows/{workflowId}")
    public WorkflowStatus getWorkflowStatus(@PathVariable String workflowId) {
        return WorkflowStatus.builder()
            .workflowId(workflowId)
            .transactionId(UUID.randomUUID().toString())
            .currentState("AML_Check_Success")
            .nextState("Payment_Gateway_Submission")
            .stepsCompleted(Arrays.asList("Initiated", "KYC_Verification", "AML_Check"))
            .executionTime("850ms")
            .build();
    }

    /**
     * 5. GET /api/v1/compliance/audit-logs
     * Returns mock audit logs for PCI-DSS/SOC2 compliance.
     */
    @GetMapping("/compliance/audit-logs")
    public List<AuditLog> getAuditLogs() {
        return Arrays.asList(
            AuditLog.builder()
                .logId(UUID.randomUUID().toString())
                .eventType("SECURE_API_ACCESS")
                .userId("svc-payment-processor")
                .ipAddress("10.0.1.45")
                .action("READ_PAYMENT_METADATA")
                .complianceTag("SOC2")
                .timestamp(LocalDateTime.now().toString())
                .build(),
            AuditLog.builder()
                .logId(UUID.randomUUID().toString())
                .eventType("CREDENTIAL_ROTATION")
                .userId("admin-ops")
                .ipAddress("172.16.0.4")
                .action("ROTATE_AWS_KMS_KEY")
                .complianceTag("PCI-DSS")
                .timestamp(LocalDateTime.now().minusDays(1).toString())
                .build()
        );
    }
}
