package com.kyc.customer.enums;

import lombok.Getter;

@Getter
public enum OperationEnum {

    INSERT(0),UPDATE(1),DELETE(2);

    private Integer operation;

    private OperationEnum(Integer value){

        this.operation = value;
    }

}
