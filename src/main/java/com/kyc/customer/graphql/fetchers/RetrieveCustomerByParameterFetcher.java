package com.kyc.customer.graphql.fetchers;

import com.kyc.customer.model.Customer;
import com.kyc.customer.repositories.jdbc.CustomerRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.Map;

import static com.kyc.customer.util.Functions.notNull;

@Component
public class RetrieveCustomerByParameterFetcher implements DataFetcher<Customer> {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        Map<String,Object> map = dataFetchingEnvironment.getArgument("filter");

        if(validMapAndElement(map,"id")){
            Integer id = Integer.valueOf(notNull(map.get("id")));
            return customerRepository.getCustomerById(id);
        }

        if(validMapAndElement(map,"rfc")){
            String rfc = notNull(map.get("rfc"));
            return customerRepository.getCustomerByRfc(rfc);
        }

        if(validMapAndElement(map,"name")){
            Map<String,Object> nameMap =(Map<String, Object>) map.get("name");
            String name = notNull(nameMap.get("firstName"));
            String secondName = notNull(nameMap.get("secondName"));
            String lastName = notNull(nameMap.get("lastName"));
            String secondLastName = notNull(nameMap.get("secondLastName"));

            Customer aux = new Customer();
            aux.setFirstName(name);
            aux.setSecondName(secondName);
            aux.setLastName(lastName);
            aux.setSecondLastName(secondLastName);

            return customerRepository.getCustomerByNames(aux);
        }

        return null;


    }

    private boolean validMapAndElement(Map map,String element){
        return map!=null && map.containsKey(element);
    }

}
