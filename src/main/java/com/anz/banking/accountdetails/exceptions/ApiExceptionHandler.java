package com.anz.banking.accountdetails.exceptions;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnProperty;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.Date;

/**
 * Global error handler for rest controllers
 * RestController advice to handle exceptions from rest methods
 */
@RestControllerAdvice
@ConditionalOnProperty(name = "account-details.controlleradvice",
        havingValue = "true")
public class ApiExceptionHandler {

    private static final Logger log = LoggerFactory.getLogger(ApiExceptionHandler.class);

    @Value("${account-details.api.version}")
    private String currentApiVersion;

    /**
     * Error handler for APIExceptions
     * @param request - request in process
     * @param ex : Api exception thrown
     * @return ResponseEntity with error
     */
    @ExceptionHandler({UserNotFoundException.class, AccountNotFoundException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<ApiError> handleNonExistingEntity(HttpServletRequest request,
                                                            ApiException ex) {
        final ApiError error = new ApiError(
                currentApiVersion,
                Integer.toString(HttpStatus.NOT_FOUND.value()),
                ex.getMessage(),
                new Date().toString(),
                request.getRequestURI()

        );
        return new ResponseEntity<>(error, HttpStatus.NOT_FOUND);
    }

}
