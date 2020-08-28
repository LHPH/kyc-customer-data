package com.kyc.customer.repositories.stores;

import com.kyc.customer.model.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;

@Repository
public class CustomerDataStore {

    @Autowired
    private DataSource dataSource;

    public void saveCustomer(Customer customer){

    }

    public int getIdCustomer(Customer customer){
        return 0;
    }

}
