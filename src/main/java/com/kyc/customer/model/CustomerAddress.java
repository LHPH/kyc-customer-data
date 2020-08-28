package com.kyc.customer.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class CustomerAddress {

    private String street;
    private String streetNumber;
    private String neighbourhood;
    private Integer idState;

}
