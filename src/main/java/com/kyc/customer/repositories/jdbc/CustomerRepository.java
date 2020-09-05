package com.kyc.customer.repositories.jdbc;

import com.kyc.customer.model.Customer;
import com.kyc.customer.repositories.mappers.CustomerMapper;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.lang.invoke.MethodHandles;
import java.util.List;
import java.util.Properties;

@Repository
public class CustomerRepository {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    @Qualifier("queriesProps")
    private Properties queriesProps;

    public List<Customer> getAllCustomers(){

        String sql = String.valueOf(queriesProps.get("getAllCustomers"));

        List<Customer> customers = jdbcTemplate.query(sql,new CustomerMapper());

        return customers;
    }

    public Customer getCustomerById(Integer id){

        String sql = String.valueOf(queriesProps.get("getCustomerById"));

        List<Customer> customers = jdbcTemplate.query(sql,new Object[]{id},new CustomerMapper());

        if(!customers.isEmpty()){
            return customers.get(0);
        }
        return null;
    }

    public Customer getCustomerByRfc(String rfc){

        String sql = String.valueOf(queriesProps.get("getCustomerByRfc"));

        List<Customer> customers = jdbcTemplate.query(sql,new Object[]{rfc},new CustomerMapper());

        if(!customers.isEmpty()){
            return customers.get(0);
        }
        return null;
    }

    public Customer getCustomerByNames(Customer dataCustomer){

        String sql = String.valueOf(queriesProps.get("getCustomerByName"));

        List<Customer> customers = jdbcTemplate.query(sql,new CustomerMapper(),
                dataCustomer.getFirstName(),dataCustomer.getSecondName(),
                dataCustomer.getLastName(),dataCustomer.getSecondLastName());

        if(!customers.isEmpty()){
            return customers.get(0);
        }
        return null;
    }

}
