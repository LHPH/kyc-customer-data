package com.kyc.customer.graphql.fetchers;

import com.kyc.customer.enums.MessageErrorEnum;
import com.kyc.customer.exceptions.CustomerException;
import com.kyc.customer.helper.CustomerHelper;
import com.kyc.customer.model.Customer;
import com.kyc.customer.repositories.jdbc.CustomerRepository;
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

import static com.kyc.customer.util.Functions.toIntegerOrNull;

@Component
public class UpdateCustomerFetcher implements DataFetcher<Customer> {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private CustomerHelper customerHelper;

    @Autowired
    private CustomerDataStore customerDataStore;

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer get(DataFetchingEnvironment dataFetch) throws Exception {

        try{
            Integer id = toIntegerOrNull(dataFetch.getArgument("id"));
            Map<String,Object> map = (Map<String, Object>) dataFetch.getArgument("customer");

            Customer customer = customerHelper.mapToModel(map);
            customer.setId(id);

            customerDataStore.updateCustomer(customer);

            return customerRepository.getCustomerById(customer.getId());
        }
        catch(Exception e){
            LOGGER.error("Ocurrio un error ",e);
            Map<String,Object> map = new HashMap<>();
            map.put("UPDATE","No se pudo actualizar la info del cliente");
            throw new CustomerException(MessageErrorEnum.ERROR_DATABASE_MODIFIED.getMessage(),map);
        }



    }
}
