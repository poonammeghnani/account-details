package com.anz.banking.accountdetails.repository;

import com.anz.banking.accountdetails.entity.Account;
import com.anz.banking.accountdetails.entity.User;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.Optional;


public interface IAccountRepository extends PagingAndSortingRepository<Account, Long> {
    /**
     * repository method to find all accounts by user with pagination
     * @param user user object to filter with
     * @param pageable pagable object for pagination
     * @return Page of Account
     */
    Page<Account> findAllByUser(User user, Pageable pageable);

    /**
     * repository method to find Account by accountNumber and user object
     * @param accountNumber - String : accountNumber to filter with
     * @param user - user to filter with
     * @return Optional of Account object
     */
    Optional<Account> findByAccountNumberAndUser(String accountNumber, User user);

}
