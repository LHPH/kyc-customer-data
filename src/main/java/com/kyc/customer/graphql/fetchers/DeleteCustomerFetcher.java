package com.kyc.customer.graphql.fetchers;

import com.kyc.customer.helper.CustomerHelper;
import com.kyc.customer.repositories.stores.CustomerDataStore;
import com.sun.org.apache.xpath.internal.operations.Bool;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import static com.kyc.customer.util.Functions.toIntegerOrNull;

@Component
public class DeleteCustomerFetcher implements DataFetcher<Boolean> {

    public static final Logger LOGGER = LogManager.getLogger(DeleteCustomerFetcher.class);

    @Autowired
    private CustomerDataStore customerDataStore;

    @Override
    public Boolean get(DataFetchingEnvironment dataFetch) throws Exception {

        Integer id = toIntegerOrNull(dataFetch.getArgument("id"));

        customerDataStore.deleteCustomer(id);

        return true;
    }
}
