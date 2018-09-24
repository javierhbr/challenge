package com.challenge.productservice.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.REQUEST_TIMEOUT, reason = "The response has been long delayed")
public class TimeOutException extends RuntimeException{

    public TimeOutException(String message)
    {
        super(message);
    }
}
