package com.application.addressbook.controllers;

import com.application.addressbook.exceptions.InvalidResourceException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;

@ControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler({InvalidResourceException.class})
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public void handleExceptionNotFound(final Exception ex) {
        logger.debug("Could not find resource", ex);
    }
}
