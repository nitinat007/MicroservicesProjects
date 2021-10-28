package com.nitin.SampleSBApplication.exception;

import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProductNotFoundException extends RuntimeException {
    private static final long serialVersionUID = 1L;

    public ProductNotFoundException(String ex) {
        super(ex);
    }

}
