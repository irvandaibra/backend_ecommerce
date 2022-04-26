package com.database.ecommerfix.excep;

public class ProductNotExistsException extends IllegalArgumentException {
    public ProductNotExistsException(String msg) {
        super (msg);
    }
}
