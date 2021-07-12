package com.anz.banking.accountdetails.exceptions;

/**
 * Custom APIException used to differentiate Application exception from other exceptions
 */
public class ApiException extends RuntimeException {
    private final int code;
    private final String message;

    public ApiException(int code, String message) {
        this.code = code;
        this.message = message;
    }

    public int code() {
        return code;
    }

    public String getMessage() {
        return message;
    }
}

