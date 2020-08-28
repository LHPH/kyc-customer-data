package com.kyc.customer.controllers;

import com.kyc.customer.services.CustomerService;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.meta.Exclusive;

@RestController
public class CustomerController {

    public static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerService customerService;

    @PostMapping("/")
    public void requestData(@RequestBody String query){

    }

    @ExceptionHandler(Exception.class)
    public Object sendError(Exception e){

        return null;
    }

}
