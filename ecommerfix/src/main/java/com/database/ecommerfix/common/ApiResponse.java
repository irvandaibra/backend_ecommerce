package com.database.ecommerfix.common;

public class ApiResponse {
    private final boolean succes;
    private final String message;

    public ApiResponse(boolean succes, String message) {
        this.succes = succes;
        this.message = message;
    }

    public boolean isSucces() {
        return succes;
    }

    public String getMessage() {
        return message;
    }
}
