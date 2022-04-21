package com.database.ecommerfix.excep;

public class CustomException extends IllegalArgumentException{
    public CustomException (String msg){
        super(msg);
    }
}
