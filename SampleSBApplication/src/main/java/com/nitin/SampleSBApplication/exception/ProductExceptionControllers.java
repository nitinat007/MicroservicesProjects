package com.nitin.SampleSBApplication.exception;

import com.nitin.SampleSBApplication.model.CustomError;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
public class ProductExceptionControllers extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> customException(ProductNotFoundException ex) {
        CustomError error = new CustomError(ex.getMessage(), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        CustomError error = new CustomError("Request body invalid. " + ex.getBindingResult(), HttpStatus.BAD_REQUEST.name());
        return new ResponseEntity<Object>(error, HttpStatus.BAD_REQUEST);
    }
}
