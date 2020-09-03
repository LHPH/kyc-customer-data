package com.kyc.customer.repositories.stores;

import com.kyc.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CustomerDataStore {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    SimpleJdbcCall simpleJdbcCall;

    public void saveCustomer(Customer customer){

        //simpleJdbcCall = new SimpleJdbcCall(dataSource).withProcedureName("PROCESS_CUSTOMER");

    }

    public void updateCustomer(Customer customer){

    }

    public void deleteCustomer(Integer id){

    }

    public int getIdCustomer(Customer customer){

        String sql = "SELECT GET_ID_CUSTOMER(?,?,?,?,?)";
        Object [] obj = new Object[]{customer.getFirstName(),customer.getSecondName(),
                customer.getLastName(),customer.getSecondLastName(),customer.getRfc()};

        return jdbcTemplate.queryForObject(sql,obj,Integer.class);
    }

}
