package com.anz.banking.accountdetails.controller;

import com.anz.banking.accountdetails.dto.AccountDetails;
import com.anz.banking.accountdetails.dto.TransactionDetails;
import com.anz.banking.accountdetails.exceptions.ApiException;
import com.anz.banking.accountdetails.service.AccountService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.annotation.Secured;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Secured("ROLE_USER")
@RestController
@RequestMapping("api")
public class ApiController {

    private static final Logger log = LoggerFactory.getLogger(ApiController.class);

    @Value("${account-details.api.version}")
    private String currentApiVersion;

    @Autowired
    private AccountService accountService;


    /**
     * get method for fetching accounts per userId
     * @param userId - record id of user
     * @param page - the page to view
     * @param size - no of records to view on the page
     * @return - list of accounts as per user and page size
     */
    @GetMapping(
            path ="/v1/user/{userId}/accounts",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<AccountDetails>> getAccounts(@PathVariable long userId, @RequestParam(defaultValue = "1") int page,
                                      @RequestParam(defaultValue = "2") int size) {
        log.info("get accounts request received");
        try {
            List<AccountDetails> result = accountService.findAccountsByUser(userId, page-1, size);
                log.debug("Account result :: " + result.size());
                return ResponseEntity.ok(result);
        }catch(ApiException ex){
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage(),ex);
        }
        catch(Exception ex){
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Service is unable to process the request",ex);
        }
    }

    /**
     * get method for fetching transactions per userId and accountNumber
     * @param userId - record id of user
     * @param accountNumber - accountNumber to filter transactions
     * @param page - the page to view
     * @param size - no of records to view on the page
     * @return - list of accounts as per user and page size
     */
    @GetMapping(
            path ="/v1/user/{userId}/account/{accountNumber}/transactions",
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public ResponseEntity<List<TransactionDetails>> getTransactions(@PathVariable long userId, @PathVariable String accountNumber,
                                                                    @RequestParam(defaultValue = "1") int page, @RequestParam(defaultValue = "2") int size) {
        log.info("get accounts request received");
        try {
            List<TransactionDetails> result = accountService.findTransactionsByUserAndAccountNumber(userId, accountNumber, page-1, size);
            log.debug("Account result :: " + result.size());
            return ResponseEntity.ok(result);
        }catch(ApiException ex){
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,ex.getMessage(),ex);
        }catch(Exception ex){
            log.error(ex.getMessage());
            throw new ResponseStatusException(HttpStatus.INTERNAL_SERVER_ERROR,"Service is unable to process the request",ex);
        }
    }

}
