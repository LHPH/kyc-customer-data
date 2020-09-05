package com.kyc.customer.graphql.fetchers;

import com.kyc.customer.enums.MessageErrorEnum;
import com.kyc.customer.exceptions.CustomerException;
import com.kyc.customer.helper.CustomerHelper;
import com.kyc.customer.model.Customer;
import com.kyc.customer.repositories.stores.CustomerDataStore;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.HashMap;
import java.util.Map;

@Component
public class AddCustomerFetcher implements DataFetcher<Integer> {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private CustomerHelper customerHelper;

    @Autowired
    private CustomerDataStore customerDataStore;

    @Override
    public Integer get(DataFetchingEnvironment dataFetch) throws Exception {

       try{
           Map<String,Object> map = (Map<String, Object>) dataFetch.getArgument("customer");
           Customer customer = customerHelper.mapToModel(map);

           customerDataStore.saveCustomer(customer);

           return customerDataStore.getIdCustomer(customer);
       }
       catch(Exception e){

           LOGGER.error("Ocurrio un error {}",e);
           Map<String,Object> map = new HashMap<>();
           map.put("ADD","No se pudo registrar la info del cliente");
           throw new CustomerException(MessageErrorEnum.ERROR_DATABASE_MODIFIED.getMessage(),map);
       }
    }
}
