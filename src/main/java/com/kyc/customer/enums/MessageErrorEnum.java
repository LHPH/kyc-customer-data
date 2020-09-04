package com.kyc.customer.enums;

import lombok.Getter;

@Getter
public enum MessageErrorEnum {

    ERROR_VALIDATION("Ocurrio un error en la validacion de datos"),
    ERROR_DATABASE_QUERY("Ocurrio un error al recuperar info de la BD"),
    ERROR_DATABASE_MODIFIED("Ocurrio un error al procesar la info en la BD"),
    ERROR_SYSTEM("Ocurrio un error desconocido");

    private String message;

    private MessageErrorEnum(String message){
        this.message = message;
    }


}
