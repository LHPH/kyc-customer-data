package com.kyc.customer.repositories.jdbc;

import com.kyc.customer.model.Customer;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class CustomerRepository {

    public static final Logger LOGGER = LogManager.getLogger(CustomerRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private List<Customer> getAllCustomers(){

        return null;
    }

    private Customer getCustomerById(Integer id){

        return null;
    }

    public Customer getCustomerByRfc(String rfc){

        return null;
    }

    public Customer getCustomerByNames(Customer dataCustomer){

        return null;
    }

}
