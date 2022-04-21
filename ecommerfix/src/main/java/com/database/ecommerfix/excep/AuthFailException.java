package com.database.ecommerfix.excep;

public class AuthFailException extends IllegalArgumentException{
    public AuthFailException (String msg){
        super(msg);
    }
}
