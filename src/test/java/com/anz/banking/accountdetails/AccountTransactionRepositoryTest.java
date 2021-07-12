package com.anz.banking.accountdetails;

import com.anz.banking.accountdetails.entity.Account;
import com.anz.banking.accountdetails.entity.User;
import com.anz.banking.accountdetails.repository.IAccountRepository;
import com.anz.banking.accountdetails.repository.IAccountTransactionRepository;
import com.anz.banking.accountdetails.repository.IUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountTransactionRepositoryTest {
    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IUserRepository userRepository;
    @Autowired
    private IAccountTransactionRepository accountTransactionRepository;

    @Test
    public void testAccountTransactionsExists() {
        User user =  userRepository.findById(1L).get();
        Account account = accountRepository.findByAccountNumberAndUser("3421123332", user).get();
        List transactions = accountTransactionRepository.findAllByAccount(account,PageRequest.of(0, 2));
        assertThat(transactions.size()>0);
    }

    @Test
    public void testAccountTransactionsPage2Exists() {
        User user =  userRepository.findById(1L).get();
        Account account = accountRepository.findByAccountNumberAndUser("3421123332", user).get();
        List transactions = accountTransactionRepository.findAllByAccount(account,PageRequest.of(1, 2));
        assertThat(transactions.size()>0);
    }

    @Test
    public void testAccountTransactionsPage3NotExists() {
        User user =  userRepository.findById(1L).get();
        Account account = accountRepository.findByAccountNumberAndUser("3421123332", user).get();
        List transactions = accountTransactionRepository.findAllByAccount(account,PageRequest.of(2, 2));
        assertThat(transactions.size()==0);
    }
}
