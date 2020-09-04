package com.kyc.customer.util;

public class Functions {

    public static String notNull(Object obj){

        if (obj==null){
            return "";
        }
        return String.valueOf(obj);
    }

    public static String notNullButIfRequired(Object obj, boolean required){

        if(obj == null && !required){
            return "";
        }
        return (obj == null) ?null : String.valueOf(obj);
    }

    public static Integer toIntegerOrNull(Object number){

        if(number == null || !number.toString().matches("\\d+")){
            return null;
        }
        return Integer.valueOf(number.toString());
    }

    public static Boolean toBooleanOrNull(Object value){
        if(value == null){
            return null;
        }
        return Boolean.valueOf(value.toString());
    }

}
