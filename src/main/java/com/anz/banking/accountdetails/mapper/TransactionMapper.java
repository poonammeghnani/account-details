package com.anz.banking.accountdetails.mapper;

import com.anz.banking.accountdetails.dto.TransactionDetails;
import com.anz.banking.accountdetails.entity.AccountTransaction;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class to map Account-Transaction entity to Account-Transaction DTO
 */
public class TransactionMapper {

    public static TransactionDetails mapTransaction(AccountTransaction transaction){
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setAccountNumber(transaction.getAccount().getAccountNumber());
        transactionDetails.setAccountName(transaction.getAccount().getAccountName());
        transactionDetails.setTransactionDate(transaction.getTransactionDate());
        transactionDetails.setCurrency(transaction.getAccount().getCurrency().getType());
        transactionDetails.setTransactionType(transaction.getType().getType());
        if(transaction.getType().getType().equals("DEBIT"))
            transactionDetails.setDebitAmount(transaction.getAmount());
        if(transaction.getType().getType().equals("CREDIT"))
            transactionDetails.setCreditAmount(transaction.getAmount());
        transactionDetails.setNarration(transaction.getNarration());
        return transactionDetails;
    }

    public static List<TransactionDetails> mapTransactions(List<AccountTransaction> transactionList){
        return transactionList.stream().map(TransactionMapper::mapTransaction).collect(Collectors.toList());
    }

}
