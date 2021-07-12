package com.anz.banking.accountdetails.service;

import com.anz.banking.accountdetails.dto.AccountDetails;
import com.anz.banking.accountdetails.dto.TransactionDetails;
import com.anz.banking.accountdetails.entity.Account;
import com.anz.banking.accountdetails.entity.AccountTransaction;
import com.anz.banking.accountdetails.entity.User;
import com.anz.banking.accountdetails.exceptions.AccountNotFoundException;
import com.anz.banking.accountdetails.exceptions.UserNotFoundException;
import com.anz.banking.accountdetails.mapper.AccountMapper;
import com.anz.banking.accountdetails.mapper.TransactionMapper;
import com.anz.banking.accountdetails.repository.IAccountRepository;
import com.anz.banking.accountdetails.repository.IAccountTransactionRepository;
import com.anz.banking.accountdetails.repository.IUserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.client.circuitbreaker.CircuitBreaker;
import org.springframework.cloud.client.circuitbreaker.CircuitBreakerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AccountService {

    private static final Logger log = LoggerFactory.getLogger(AccountService.class);

    @Autowired
    IAccountRepository accountRepository;

    @Autowired
    IAccountTransactionRepository accountTransactionRepository;

    @Autowired
    IUserRepository userRepository;

    @Autowired
    CircuitBreakerFactory circuitBreakerFactory;

    /**
     * Service method to facilitate retrieval of accounts by userId
     * @param userId - recordId of user
     * @param page - page to view
     * @param size - number of records per page
     * @return List of Account DTO as per userId and page size
     */
    public List<AccountDetails> findAccountsByUser(long userId, int page, int size) {
        log.debug("inside AccountService::findAccountsByUser with userId {}", userId);
        CircuitBreaker databaseAccessCircuitBreaker = circuitBreakerFactory.create("databaseAccessCircuitBreaker");
        return databaseAccessCircuitBreaker.run( () -> {
            User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            Page<Account> accountList = accountRepository.findAllByUser(user, PageRequest.of(page, size));
            return AccountMapper.mapAccounts(accountList);
            });
    }

    /**
     * Service method to facilitate retrieval of transactions by userId and accountNumber
     * @param userId - recordId of user
     * @param accountNumber - account number to filter with
     * @param page - page to view
     * @param size - number of records per page
     * @return List of Transaction DTO as per userId and accountNumber and page size
     */
    public List<TransactionDetails> findTransactionsByUserAndAccountNumber(long userId, String accountNumber, int page, int size) {

        log.debug("inside AccountService::findTransactionsByUserAndAccountNumber with userId {} & accountNumber {}", userId, accountNumber);
        CircuitBreaker databaseAccessCircuitBreaker = circuitBreakerFactory.create("databaseAccessCircuitBreaker");
        return databaseAccessCircuitBreaker.run( () -> {
            User user = userRepository.findById(userId).orElseThrow(UserNotFoundException::new);
            Account account = accountRepository.findByAccountNumberAndUser(accountNumber, user).orElseThrow(AccountNotFoundException::new);
            List<AccountTransaction> accountTransactionList =  accountTransactionRepository.findAllByAccount(account, PageRequest.of(page, size));
            return TransactionMapper.mapTransactions(accountTransactionList);
        });

    }

}
