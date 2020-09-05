package com.kyc.customer.graphql.fetchers;

import com.kyc.customer.enums.MessageErrorEnum;
import com.kyc.customer.exceptions.CustomerException;
import com.kyc.customer.model.Customer;
import com.kyc.customer.model.CustomerAddress;
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
public class DeleteCustomerFetcher implements DataFetcher<Boolean> {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private CustomerDataStore customerDataStore;

    @Override
    public Boolean get(DataFetchingEnvironment dataFetch) throws Exception {

        Integer id = toIntegerOrNull(dataFetch.getArgument("id"));

        try{
            Customer customer = new Customer();
            CustomerAddress address = new CustomerAddress();
            customer.setAddress(address);
            customer.setId(id);
            customerDataStore.deleteCustomer(customer);

            return true;
        }
        catch(Exception e){
            LOGGER.error("Ocurrio un error {}",e);
            Map<String,Object> map = new HashMap<>();
            map.put("DELETE","No se pudo eliminar al cliente");
            throw new CustomerException(MessageErrorEnum.ERROR_DATABASE_MODIFIED.getMessage(),map);
        }
    }
}
