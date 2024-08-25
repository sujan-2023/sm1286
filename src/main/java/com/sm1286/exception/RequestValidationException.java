package com.sm1286.exception;

import javax.xml.bind.ValidationException;

public class RequestValidationException extends ValidationException {
    public RequestValidationException(String message) {
        super(message);
    }
}
