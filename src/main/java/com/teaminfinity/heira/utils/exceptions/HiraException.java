package com.teaminfinity.heira.utils.exceptions;

import lombok.Data;
import lombok.EqualsAndHashCode;
import org.springframework.http.HttpStatus;

@EqualsAndHashCode(callSuper = true)
@Data
public class HiraException extends RuntimeException{
    private String origin;
    private HttpStatus status;

    public HiraException(String origin,String message, HttpStatus status) {
        super(message);
        this.origin = origin;
        this.status = status;
    }
}
