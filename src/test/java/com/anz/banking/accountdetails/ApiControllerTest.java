package com.anz.banking.accountdetails;

import com.anz.banking.accountdetails.controller.ApiController;
import com.anz.banking.accountdetails.dto.AccountDetails;
import com.anz.banking.accountdetails.dto.TransactionDetails;
import com.anz.banking.accountdetails.service.AccountService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.Base64;
import java.util.Date;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.hamcrest.Matchers.is;
import static org.mockito.BDDMockito.given;
import static org.springframework.restdocs.mockmvc.RestDocumentationRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringJUnit4ClassRunner.class)
@WebMvcTest(ApiController.class)
public class ApiControllerTest {

    @Autowired
    private MockMvc mvc;

    @MockBean
    private AccountService accountService;

    @Test
    public void givenAccounts_whenGetAccounts_thenReturnJsonArray()
            throws Exception {

        Base64.Encoder encoder = Base64.getEncoder();
        String encoding = encoder.encodeToString(("test-user" + ":" + "P@ssword").getBytes());

        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountNumber("3421123332");
        accountDetails.setAccountName("SGSavings726");
        accountDetails.setCurrency("AUD");
        accountDetails.setBalanceDate(new Date());
        accountDetails.setAvailableBalance(1234.56);
        List<AccountDetails> allAccounts = Arrays.asList(accountDetails);

        given(accountService.findAccountsByUser(1, 0, 2)).willReturn(allAccounts);

        mvc.perform(get("/api/user/{userId}/accounts",1).header("Authorization", "Basic " + encoding)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].accountName", is(accountDetails.getAccountName())))
                .andExpect(jsonPath("$[0].accountNumber", is(accountDetails.getAccountNumber())));

    }

    @Test
    public void givenNoAccounts_whenGetAccounts_thenReturnEmptyJsonArray()
            throws Exception {

        Base64.Encoder encoder = Base64.getEncoder();
        String encoding = encoder.encodeToString(("test-user" + ":" + "P@ssword").getBytes());

        AccountDetails accountDetails = new AccountDetails();
        accountDetails.setAccountNumber("3421123332");
        accountDetails.setAccountName("SGSavings726");
        accountDetails.setCurrency("AUD");
        accountDetails.setBalanceDate(new Date());
        accountDetails.setAvailableBalance(1234.56);
        List<AccountDetails> allAccounts = Arrays.asList(accountDetails);

        given(accountService.findAccountsByUser(1, 0, 2)).willReturn(allAccounts);

        mvc.perform(get("/api/user/{userId}/accounts",2).header("Authorization", "Basic " + encoding)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

    @Test
    public void givenTransactons_whenGetTransactions_thenReturnJsonArray()
            throws Exception {

        Base64.Encoder encoder = Base64.getEncoder();
        String encoding = encoder.encodeToString(("test-user" + ":" + "P@ssword").getBytes());
        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setAccountNumber("3421123332");
        transactionDetails.setAccountName("SGSavings726");
        transactionDetails.setCurrency("AUD");
        transactionDetails.setTransactionType("CREDIT");
        transactionDetails.setCreditAmount(1234.56);
        transactionDetails.setNarration("Salary credited");
        List<TransactionDetails> allTransactions = Arrays.asList(transactionDetails);

        given(accountService.findTransactionsByUserAndAccountNumber(1, "3421123332",0,1)).willReturn(allTransactions);

        mvc.perform(get("/api/user/{userId}/account/{accountNumber}/transactions",1, "3421123332").header("Authorization", "Basic " + encoding)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(1)))
                .andExpect(jsonPath("$[0].accountName", is(transactionDetails.getAccountName())))
                .andExpect(jsonPath("$[0].accountNumber", is(transactionDetails.getAccountNumber())))
                .andExpect(jsonPath("$[0].transactionType", is(transactionDetails.getTransactionType())))
                .andExpect(jsonPath("$[0].debitAmount", is(transactionDetails.getDebitAmount())))
                .andExpect(jsonPath("$[0].creditAmount", is(transactionDetails.getCreditAmount())))
                .andExpect(jsonPath("$[0].narration", is(transactionDetails.getNarration())));




    }

    @Test
    public void givenNoTransaction_whenGetTransaction_thenReturnEmptyJsonArray()
            throws Exception {
        Base64.Encoder encoder = Base64.getEncoder();
        String encoding = encoder.encodeToString(("test-user" + ":" + "P@ssword").getBytes());

        TransactionDetails transactionDetails = new TransactionDetails();
        transactionDetails.setAccountNumber("3421123332");
        transactionDetails.setAccountName("SGSavings726");
        transactionDetails.setCurrency("AUD");
        transactionDetails.setTransactionType("CREDIT");
        transactionDetails.setCreditAmount(1234.56);
        transactionDetails.setNarration("Salary credited");
        List<TransactionDetails> allTransactions = Arrays.asList(transactionDetails);

        mvc.perform(get("/api/user/{userId}/account/{accountNumber}/transactions",2, "3421123331").header("Authorization", "Basic " + encoding)
                .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$", hasSize(0)));

    }

}
