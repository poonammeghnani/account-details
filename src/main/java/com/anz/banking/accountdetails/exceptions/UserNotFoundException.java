package com.anz.banking.accountdetails.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to handle missing user
 */
@ResponseStatus(code = HttpStatus.NOT_FOUND)
public class UserNotFoundException extends ApiException{

    public UserNotFoundException() {
        super(2, "User Not Found");
    }
}
