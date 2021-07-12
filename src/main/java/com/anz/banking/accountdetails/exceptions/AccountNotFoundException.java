package com.anz.banking.accountdetails.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to manage unknown Account access
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class AccountNotFoundException extends ApiException{
    public AccountNotFoundException() {
        super(3, "Account Not Found");
    }
}
