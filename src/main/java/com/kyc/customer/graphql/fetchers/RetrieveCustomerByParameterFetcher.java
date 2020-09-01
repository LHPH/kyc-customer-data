package com.kyc.customer.graphql.fetchers;

import com.kyc.customer.controllers.CustomerController;
import com.kyc.customer.model.Customer;
import com.kyc.customer.repositories.jdbc.CustomerRepository;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class RetrieveCustomerByParameterFetcher implements DataFetcher<Customer> {

    public static final Logger LOGGER = LogManager.getLogger(CustomerController.class);

    @Autowired
    private CustomerRepository customerRepository;

    @Override
    public Customer get(DataFetchingEnvironment dataFetchingEnvironment) throws Exception {

        Map<String,Object> map = dataFetchingEnvironment.getArgument("filter");

        if(validMapAndElement(map,"id")){
            Integer id = Integer.valueOf(String.valueOf(map.get("id")));
            return customerRepository.getCustomerById(id);
        }

        if(validMapAndElement(map,"rfc")){
            String rfc = String.valueOf(map.get("rfc"));
            return customerRepository.getCustomerByRfc(rfc);
        }

        if(validMapAndElement(map,"name")){
            Map<String,Object> nameMap =(Map<String, Object>) map.get("name");
            String name = nameMap.get("firstName").toString();
            String secondName = nameMap.get("secondName").toString();
            String lastName = nameMap.get("lastName").toString();
            String secondLastName = nameMap.get("secondLastName").toString();

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
