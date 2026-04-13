# Fintech Payments Backend (B2B)

This is a Java-based Spring Boot backend prototype for a B2B payment platform, inspired by high-availability financial systems. It focuses on scalability, compliance, and multi-currency payment workflows.

## API Endpoints (v1)

### 1. List Recent Transactions
- **Endpoint:** `GET /api/v1/payments`
- **Description:** Returns a list of recent B2B payments with ISO20022 metadata.
- **Sample Request:**
  ```bash
  curl -X GET http://localhost:8080/api/v1/payments
  ```
- **Sample Response:**
  ```json
  [
    {
      "transactionId": "550e8400-e29b-41d4-a716-446655440000",
      "senderName": "Global Logistics Inc.",
      "receiverName": "Pacific Shipping Co.",
      "amount": 1250000.00,
      "currency": "USD",
      "status": "COMPLETED",
      "paymentMethod": "SWIFT",
      "iso20022Code": "pacs.008.001.07",
      "timestamp": "2026-04-13T09:20:00"
    }
  ]
  ```

### 2. Initiate Payment Workflow
- **Endpoint:** `POST /api/v1/payments/initiate`
- **Description:** Triggers a mock Step Functions workflow for transaction processing.
- **Sample Request:**
  ```bash
  curl -X POST http://localhost:8080/api/v1/payments/initiate \
    -H "Content-Type: application/json" \
    -d '{
      "senderName": "TechVentures LLC",
      "receiverName": "Cloud Infra Corp",
      "amount": 50000.0,
      "currency": "EUR",
      "paymentMethod": "ACH"
    }'
  ```
- **Sample Response:**
  ```json
  {
    "workflowId": "wf-8a3b2c1d",
    "transactionId": "b2c1d8a3-e29b-41d4-a716-446655440000",
    "currentState": "Initiated",
    "nextState": "KYC_Verification",
    "stepsCompleted": ["Validation", "Duplicate_Check"],
    "executionTime": "145ms"
  }
  ```

### 3. Account Balances
- **Endpoint:** `GET /api/v1/accounts/balance`
- **Description:** Fetches current balances for settlement and reserve accounts.
- **Sample Request:**
  ```bash
  curl -X GET http://localhost:8080/api/v1/accounts/balance
  ```
- **Sample Response:**
  ```json
  [
    {
      "accountId": "ACC-88219",
      "accountType": "Main Settlement",
      "balance": 42500000.00,
      "currency": "USD",
      "lastUpdated": "2026-04-13T11:20:31"
    }
  ]
  ```

### 4. Workflow Tracking
- **Endpoint:** `GET /api/v1/workflows/{workflowId}`
- **Description:** Status tracking for event-driven payment pipelines.
- **Sample Request:**
  ```bash
  curl -X GET http://localhost:8080/api/v1/workflows/wf-8a3b2c1d
  ```
- **Sample Response:**
  ```json
  {
    "workflowId": "wf-8a3b2c1d",
    "transactionId": "c4d5e6f7-g8h9-i10j-k11l-m12n13o14p15",
    "currentState": "AML_Check_Success",
    "nextState": "Payment_Gateway_Submission",
    "stepsCompleted": ["Initiated", "KYC_Verification", "AML_Check"],
    "executionTime": "850ms"
  }
  ```

### 5. Compliance Audit Logs
- **Endpoint:** `GET /api/v1/compliance/audit-logs`
- **Description:** Mock audit logs for SOC2 and PCI-DSS monitoring.
- **Sample Request:**
  ```bash
  curl -X GET http://localhost:8080/api/v1/compliance/audit-logs
  ```
- **Sample Response:**
  ```json
  [
    {
      "logId": "log-778899",
      "eventType": "SECURE_API_ACCESS",
      "userId": "svc-payment-processor",
      "ipAddress": "10.0.1.45",
      "action": "READ_PAYMENT_METADATA",
      "complianceTag": "SOC2",
      "timestamp": "2026-04-13T10:15:00"
    }
  ]
  ```

## Technical Details
- **Framework:** Spring Boot 3.2.4
- **Language:** Java 17
- **Models:** Includes B2B payment primitives (ISO20022, Multi-currency).
- **Compliance:** Designed with auditability and secure access patterns in mind.
