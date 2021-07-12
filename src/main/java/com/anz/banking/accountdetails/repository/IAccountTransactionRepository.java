package com.anz.banking.accountdetails.repository;

import com.anz.banking.accountdetails.entity.Account;
import com.anz.banking.accountdetails.entity.AccountTransaction;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IAccountTransactionRepository extends CrudRepository<AccountTransaction, Long> {

    /**
     * repository method to find transactions by Account object
     * @param account :Account :: account object to filter with
     * @param pageable: Pagable :: pagable params for pagination
     * @return - List of AccountTransactions
     */
    List<AccountTransaction> findAllByAccount(Account account, Pageable pageable);
}
