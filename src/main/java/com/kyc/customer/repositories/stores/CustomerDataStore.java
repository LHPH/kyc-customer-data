package com.kyc.customer.repositories.stores;

import com.kyc.customer.enums.OperationEnum;
import com.kyc.customer.model.Customer;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Repository;
import org.springframework.beans.factory.annotation.Value;

import javax.annotation.PostConstruct;
import java.lang.invoke.MethodHandles;
import java.util.Properties;

@Repository
public class CustomerDataStore {

    public static final Logger LOGGER = LogManager.getLogger(MethodHandles.lookup().lookupClass());

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    @Qualifier("queriesProps")
    private Properties queriesProps;

    @PostConstruct
    public void init(){
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate).withProcedureName("PROCESS_CUSTOMER");
    }

    public void saveCustomer(Customer customer){

        LOGGER.info("Ejecutando SP con Operacion {}",OperationEnum.INSERT);
        SqlParameterSource params = getParametersUp(customer,OperationEnum.INSERT);
        simpleJdbcCall.execute(params);
        LOGGER.info("Se termino de ejecutar SP con Operacion {}",OperationEnum.INSERT);
    }

    public void updateCustomer(Customer customer){

        LOGGER.info("Ejecutando SP con Operacion {}",OperationEnum.UPDATE);
        SqlParameterSource params = getParametersUp(customer,OperationEnum.UPDATE);

        String sql = queriesProps.get("SP_PROCESS_CUSTOMER").toString();
        namedParameterJdbcTemplate.update(sql,params);
        //simpleJdbcCall.execute(params);
        LOGGER.info("Se termino de ejecutar SP con Operacion {}",OperationEnum.UPDATE);
    }

    public void deleteCustomer(Customer customer){

        LOGGER.info("Ejecutando SP con Operacion {}",OperationEnum.DELETE);
        SqlParameterSource params = getParametersUp(customer,OperationEnum.DELETE);
        simpleJdbcCall.execute(params);
        LOGGER.info("Se termino de ejecutar SP con Operacion {}",OperationEnum.DELETE);
    }

    public int getIdCustomer(Customer customer){

        String sql = "SELECT GET_ID_CUSTOMER(?,?,?,?,?)";
        Object [] obj = new Object[]{customer.getFirstName(),customer.getSecondName(),
                customer.getLastName(),customer.getSecondLastName(),customer.getRfc()};

        return jdbcTemplate.queryForObject(sql,obj,Integer.class);
    }

    private SqlParameterSource getParametersUp(Customer customer, OperationEnum operation){

        SqlParameterSource in = new MapSqlParameterSource()
                .addValue("OPERATION",operation.getOperation())
                .addValue("P_ID_CUSTOMER",customer.getId())
                .addValue("P_FIRST_NAME",customer.getFirstName())
                .addValue("P_SECOND_NAME",customer.getSecondName())
                .addValue("P_LAST_NAME",customer.getLastName())
                .addValue("P_SECOND_LAST_NAME",customer.getSecondLastName())
                .addValue("P_RFC",customer.getRfc())
                .addValue("P_AGE",customer.getAge())
                .addValue("P_HOME_PHONE",customer.getHomePhone())
                .addValue("P_CELL_PHONE",customer.getCellPhone())
                .addValue("P_EMAIL",customer.getEmail())
                .addValue("P_ACTIVE",determinateActive(customer.getActive(),operation))
                .addValue("P_STREET",customer.getAddress().getStreet())
                .addValue("P_STREET_NUMBER",customer.getAddress().getStreetNumber())
                .addValue("P_NEIGHBOURHOOD",customer.getAddress().getNeighbourhood())
                .addValue("P_ID_STATE",customer.getAddress().getIdState());

        return in;
    }

    private Boolean determinateActive(Boolean value,OperationEnum operation){

        switch(operation){
            case INSERT:
                return Boolean.TRUE;
            case DELETE:
                return Boolean.FALSE;
            default:
                return value;
        }

    }

}
