package com.app.rotativeturnsapi.exceptions;

public class AmountOfFreeDaysPerWeekException extends IllegalArgumentException {

    public AmountOfFreeDaysPerWeekException(String message) {
        super(message);
    }

    public AmountOfFreeDaysPerWeekException(Throwable cause) {
        super(cause);
    }

    public AmountOfFreeDaysPerWeekException(String message, Throwable cause) {
        super(message, cause);
    }
}
