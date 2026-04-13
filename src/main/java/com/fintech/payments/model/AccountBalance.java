package com.fintech.payments.model;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class AccountBalance {
    private String accountId;
    private String accountType; // Settlement, Operating, Reserve
    private Double balance;
    private String currency;
    private String lastUpdated;
}
