package com.nitin.SampleSBApplication.model;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data
public class CustomError {
    String message;
    String errorCode;
}
