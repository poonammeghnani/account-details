package com.anz.banking.accountdetails.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
public class TransactionDetails {

    private String accountNumber;
    private String accountName;
    private Date transactionDate;
    private String currency;
    private double debitAmount;
    private double creditAmount;
    private String transactionType;
    private String narration;

}
