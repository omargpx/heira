package com.teaminfinity.heira.utils.exceptions;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.LocalDateTime;

@ControllerAdvice
public class DefaultExceptionHandler {

    @ExceptionHandler(value = {HiraException.class})
    public ResponseEntity<ApiError> handleException(HiraException ke,
                                                    HttpServletRequest request){
        ApiError apiError = new ApiError(
          request.getRequestURI(),
          ke.getMessage(),
          ke.getStatus().value(),
          LocalDateTime.now()
        );
        return new ResponseEntity<>(apiError,ke.getStatus());
    }
}
