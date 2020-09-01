package com.kyc.customer.graphql.fetchers;

import com.kyc.customer.model.Customer;
import com.kyc.customer.repositories.jdbc.CustomerRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class RetrieveAllCustomerFetcher implements DataFetcher<List<Customer>> {

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public List<Customer> get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        return customerRepository.getAllCustomers();
    }
}
