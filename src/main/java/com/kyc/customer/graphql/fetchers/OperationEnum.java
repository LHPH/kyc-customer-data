package com.kyc.customer.graphql.fetchers;

import lombok.Getter;

@Getter
public enum OperationEnum {

    INSERT(0),UPDATE(1),DELETE(2);

    private int value;

    private OperationEnum(int value){
        this.value = value;
    }

}
