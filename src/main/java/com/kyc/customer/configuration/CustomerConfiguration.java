package com.kyc.customer.configuration;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

@Configuration
public class CustomerConfiguration {

    //Ref:https://stackoverflow.com/questions/15433032/clean-way-to-externalize-long-20-lines-sql-when-using-spring-jdbc
    @Bean
    public PropertiesFactoryBean queriesProps(){

        PropertiesFactoryBean props = new PropertiesFactoryBean();
        props.setLocation(new ClassPathResource("sql/queries.xml"));
        return props;

    }

}
