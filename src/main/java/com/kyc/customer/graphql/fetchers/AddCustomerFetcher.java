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

@Component
public class AddCustomerFetcher implements DataFetcher<Integer> {

    public static final Logger LOGGER = LogManager.getLogger(AddCustomerFetcher.class);

    @Autowired
    private CustomerHelper customerHelper;

    @Autowired
    private CustomerDataStore customerDataStore;

    @Override
    public Integer get(DataFetchingEnvironment dataFetch) throws Exception {

        Map<String,Object> map = (Map<String, Object>) dataFetch.getArgument("customer");

        Customer customer = customerHelper.mapToModel(map);

        customerDataStore.saveCustomer(customer);

        return 2;
    }
}
