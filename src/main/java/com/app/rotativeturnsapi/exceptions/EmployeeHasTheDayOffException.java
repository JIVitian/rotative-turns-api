package com.app.rotativeturnsapi.exceptions;

public class EmployeeHasTheDayOffException  extends IllegalArgumentException {

    public EmployeeHasTheDayOffException(String message) {
        super(message);
    }

    public EmployeeHasTheDayOffException(Throwable cause) {
        super(cause);
    }

    public EmployeeHasTheDayOffException(String message, Throwable cause) {
        super(message, cause);
    }
}
