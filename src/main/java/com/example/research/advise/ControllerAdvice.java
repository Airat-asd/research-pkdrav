package com.example.research.advise;

import java.util.List;
import java.util.stream.Collectors;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.NoHandlerFoundException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;
import com.example.research.advise.handler.CustomExceptionHandler;
import com.example.research.dto.Error;

@RestControllerAdvice
@Slf4j
public class ControllerAdvice extends ResponseEntityExceptionHandler {

    public static final String X_STATUS = "X-Status";

    public static final String X_STATUS_REASON = "X-Status-Reason";

    @Autowired
    CustomExceptionHandler exHandler;

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
        MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status,
        WebRequest request) {

        headers.add(X_STATUS, "Invalid");
        headers.add(X_STATUS_REASON, "Validation failed");

        List<String> globalErrors = ex.getBindingResult()
            .getGlobalErrors()
            .stream()
            .map(x -> "Invalid Request for JSON ('" + x.getObjectName() + "'): " + x
                .getDefaultMessage())
            .collect(Collectors.toList());

        List<String> fieldErrors = ex.getBindingResult()
            .getFieldErrors()
            .stream()
            .map(x -> "Invalid value for field '" + x.getField() + "': " + x.getDefaultMessage())
            .collect(Collectors.toList());

        globalErrors.addAll(fieldErrors);

        return new ResponseEntity<>(
            new Error(
                "Invalid request",
                HttpStatus.BAD_REQUEST.value(),
                System.currentTimeMillis(),
                globalErrors
            ),
            headers,
            HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<Error> handleOtherExceptions(Exception e,
        HttpServletRequest request, HttpServletResponse response) {
        return exHandler.handleOtherExceptions(e, request, response);
    }

    @Override
    protected ResponseEntity<Object> handleNoHandlerFoundException(
        NoHandlerFoundException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        log.trace("inside advice handleNoHandlerFoundException");
        headers.add(X_STATUS, "Error");
        headers.add(X_STATUS_REASON, "Resource not found");

        return new ResponseEntity<>(
            new Error(
                "Resource not found",
                HttpStatus.NOT_FOUND.value(),
                System.currentTimeMillis()
            ),
            headers,
            HttpStatus.NOT_FOUND
        );
    }

}
