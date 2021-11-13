package com.nitin.SampleSBApplication.model;

import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
public class Product {
    int id;

    @NotNull(message = "name can not be null")
    @Size(min = 3, message = "name must be more than 3 char long")
    String name;

    @Positive(message = "price must be a positive number")
    int price;


}
