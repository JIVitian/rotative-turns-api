package com.app.rotativeturnsapi.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class WorkdayDurationException extends IllegalArgumentException {

    public WorkdayDurationException(String message) {
        super(message);
    }

    public WorkdayDurationException(Throwable cause) {
        super(cause);
    }

    public WorkdayDurationException(String message, Throwable cause) {
        super(message, cause);
    }
}
