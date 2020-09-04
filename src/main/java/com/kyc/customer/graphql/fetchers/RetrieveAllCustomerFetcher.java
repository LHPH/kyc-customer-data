package com.kyc.customer.graphql.fetchers;

import com.kyc.customer.model.Customer;
import com.kyc.customer.repositories.jdbc.CustomerRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.List;

@Component
public class RetrieveAllCustomerFetcher implements DataFetcher<List<Customer>> {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        return customerRepository.getAllCustomers();
    }
}
