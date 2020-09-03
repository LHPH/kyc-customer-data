package com.kyc.customer.graphql.fetchers;

import com.kyc.customer.helper.CustomerHelper;
import com.kyc.customer.model.Customer;
import com.kyc.customer.repositories.stores.CustomerDataStore;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

import static com.kyc.customer.util.Functions.toIntegerOrNull;

@Component
public class UpdateCustomerFetcher implements DataFetcher<Customer> {

    public static final Logger LOGGER = LogManager.getLogger(UpdateCustomerFetcher.class);

    @Autowired
    private CustomerHelper customerHelper;

    @Autowired
    private CustomerDataStore customerDataStore;

    @Override
    public Customer get(DataFetchingEnvironment dataFetch) throws Exception {

        Integer id = toIntegerOrNull(dataFetch.getArgument("id"));
        Map<String,Object> map = (Map<String, Object>) dataFetch.getArgument("customer");

        Customer customer = customerHelper.mapToModel(map);
        customer.setId(id);

        customerDataStore.updateCustomer(customer);

        return customer;
    }
}
