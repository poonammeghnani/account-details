package com.anz.banking.accountdetails;

import com.anz.banking.accountdetails.entity.Account;
import com.anz.banking.accountdetails.entity.User;
import com.anz.banking.accountdetails.repository.IAccountRepository;
import com.anz.banking.accountdetails.repository.IUserRepository;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;

@RunWith(SpringRunner.class)
@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class AccountRepositoryTest {

    @Autowired
    private IAccountRepository accountRepository;
    @Autowired
    private IUserRepository userRepository;


    @Test
    public void testAccountExists() {
        User user =  userRepository.findById(1L).get();
        Page<Account> accountList = accountRepository.findAllByUser(user, PageRequest.of(0, 2));
        assertThat(accountList.getContent().size()>0);
    }

    @Test
    public void testAccountPage2Exists() {
        User user =  userRepository.findById(1L).get();
        Page<Account> accountList = accountRepository.findAllByUser(user, PageRequest.of(1, 2));
        assertThat(accountList.getContent().size()>0);
    }

    @Test
    public void testAccountPage3NotExists() {
        User user =  userRepository.findById(1L).get();
        Page<Account> accountList = accountRepository.findAllByUser(user, PageRequest.of(2, 2));
        assertThat(accountList.getContent().size()==0);
    }

}
