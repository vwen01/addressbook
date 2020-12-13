package com.application.addressbook.controllers;

import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DefaultErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping(value = PATH)
    public ResponseEntity<String> error() {
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
