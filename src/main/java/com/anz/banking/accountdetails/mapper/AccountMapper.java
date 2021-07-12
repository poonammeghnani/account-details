package com.anz.banking.accountdetails.mapper;

import com.anz.banking.accountdetails.dto.AccountDetails;
import com.anz.banking.accountdetails.entity.Account;
import org.springframework.data.domain.Page;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Mapper class to map account entity with account DTO
 */
public class AccountMapper {

    public static AccountDetails mapAccount(Account account){
        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountName(account.getAccountName());
        accountDetails.setAccountNumber(account.getAccountNumber());
        accountDetails.setAccountType(account.getAccountType().getType());
        accountDetails.setBalanceDate(account.getBalanceDate());
        accountDetails.setCurrency(account.getCurrency().getType());
        accountDetails.setAvailableBalance(account.getAvailableBalance());
        return accountDetails;
    }

    public static List<AccountDetails> mapAccounts(Page<Account> accounts){
        return accounts.getContent().stream().map(AccountMapper::mapAccount).collect(Collectors.toList());
    }

}
