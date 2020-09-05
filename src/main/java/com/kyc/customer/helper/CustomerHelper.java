package com.kyc.customer.helper;

import com.kyc.customer.model.Customer;
import com.kyc.customer.model.CustomerAddress;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import java.lang.invoke.MethodHandles;
import java.util.Map;

import static com.kyc.customer.util.Functions.notNullButIfRequired;
import static com.kyc.customer.util.Functions.toIntegerOrNull;
import static com.kyc.customer.util.Functions.toBooleanOrNull;

@Component
public class CustomerHelper {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

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
        customer.setActive(toBooleanOrNull(map.get("active")));

        Map<String,Object > mapAddress = (Map<String, Object>) map.get("address");
        if(mapAddress!=null){

            address.setStreet(notNullButIfRequired(mapAddress.get("street"),true));
            address.setStreetNumber(notNullButIfRequired(mapAddress.get("streetNumber"),true));
            address.setNeighbourhood(notNullButIfRequired(mapAddress.get("neighbourhood"),true));
            address.setIdState(toIntegerOrNull(mapAddress.get("idState")));

        }
        customer.setAddress(address);

        LOGGER.info("Customer: {}",customer);
        return customer;

    }

}
