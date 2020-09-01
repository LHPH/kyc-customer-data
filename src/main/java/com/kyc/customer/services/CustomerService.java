package com.kyc.customer.services;

import com.kyc.customer.graphql.GraphQLProvider;
import graphql.ExecutionResult;
import graphql.GraphQL;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CustomerService {

    public static final Logger LOGGER = LogManager.getLogger(CustomerService.class);

    @Autowired
    //private GraphQLProvider graphQLProvider;
    private GraphQL graphQL;


    public ExecutionResult processOperationCustomer(String operation){

        return graphQL.execute(operation);

    }

}
