package com.app.rotativeturnsapi.exceptions;

public class AmountOfEmployeesException extends IllegalArgumentException {

    public AmountOfEmployeesException(String message) {
        super(message);
    }

    public AmountOfEmployeesException(Throwable cause) {
        super(cause);
    }

    public AmountOfEmployeesException(String message, Throwable cause) {
        super(message, cause);
    }
}
