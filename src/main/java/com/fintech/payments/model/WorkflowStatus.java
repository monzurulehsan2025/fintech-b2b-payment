package com.fintech.payments.model;

import lombok.Builder;
import lombok.Data;
import java.util.List;

@Data
@Builder
public class WorkflowStatus {
    private String workflowId;
    private String transactionId;
    private String currentState; // Initiated, KYC_Check, AML_Check, Processing, Completed
    private String nextState;
    private List<String> stepsCompleted;
    private String executionTime;
}
