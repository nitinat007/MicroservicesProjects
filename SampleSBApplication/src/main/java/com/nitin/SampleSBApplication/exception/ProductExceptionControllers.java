package com.nitin.SampleSBApplication.exception;

import com.nitin.SampleSBApplication.model.CustomError;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class ProductExceptionControllers {

    @ExceptionHandler(value = ProductNotFoundException.class)
    public ResponseEntity<Object> customException(ProductNotFoundException ex) {
        CustomError error = new CustomError(ex.getMessage(), HttpStatus.NOT_FOUND.toString());
        return new ResponseEntity<Object>(error, HttpStatus.NOT_FOUND);
    }
}
