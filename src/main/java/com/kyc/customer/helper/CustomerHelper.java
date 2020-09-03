package com.kyc.customer.helper;

import com.kyc.customer.model.Customer;
import com.kyc.customer.model.CustomerAddress;
import org.springframework.stereotype.Component;

import static com.kyc.customer.util.Functions.toIntegerOrNull;
import static com.kyc.customer.util.Functions.notNullButIfRequired;

import java.util.Map;

@Component
public class CustomerHelper {

    public Customer mapToModel(Map<String,Object> map){

        Customer customer = new Customer();
        CustomerAddress address = new CustomerAddress();

        customer.setFirstName(notNullButIfRequired(map.get("firstName"),true));
        customer.setSecondName(notNullButIfRequired(map.get("secondName"),false));
        customer.setLastName(notNullButIfRequired(map.get("lastName"),true));
        customer.setSecondLastName(notNullButIfRequired(map.get("secondLastName"),true));
        customer.setRfc(notNullButIfRequired(map.get("rfc"),true));
        customer.setAge(notNullButIfRequired(map.get("age"),true));
        customer.setHomePhone(notNullButIfRequired(map.get("homePhone"),false));
        customer.setCellPhone(notNullButIfRequired(map.get("cellPhone"),true));
        customer.setEmail(notNullButIfRequired(map.get("email"),true));
        
        Map<String,Object > mapAddress = (Map<String, Object>) map.get("address");
        if(mapAddress!=null){

            address.setStreet(notNullButIfRequired(map.get("street"),true));
            address.setStreetNumber(notNullButIfRequired(map.get("streetNumber"),true));
            address.setNeighbourhood(notNullButIfRequired(map.get("neighbourhood"),true));
            address.setIdState(toIntegerOrNull(map.get("idState")));

            customer.setAddress(address);
        }

        return customer;

    }

}
