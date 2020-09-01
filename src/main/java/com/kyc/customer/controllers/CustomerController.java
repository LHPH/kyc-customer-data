package com.kyc.customer.controllers;

import com.kyc.customer.services.CustomerService;
import graphql.ExecutionResult;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
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
    public ResponseEntity<Object> requestData(@RequestBody String query){
        LOGGER.info("Entrada {}",query);
        ExecutionResult executionResult = customerService.processOperationCustomer(query);
        return new ResponseEntity<>(executionResult, HttpStatus.OK);

    }

    @ExceptionHandler(Exception.class)
    public Object sendError(Exception e){
        LOGGER.error("Ocurrio un error {}",e);
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
