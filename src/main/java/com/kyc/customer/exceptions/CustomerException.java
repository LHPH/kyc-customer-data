package com.kyc.customer.exceptions;


import lombok.Getter;

import java.util.Map;

@Getter
public class CustomerException extends RuntimeException {

    private Map<String, Object> metaData;

    public CustomerException(String message, Map<String,Object> metadata){
        super(message);
        this.metaData = metadata;
    }
}
