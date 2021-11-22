package com.nitin.SampleSBApplication.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;
import javax.validation.constraints.Size;

@Data
// @JsonIgnoreProperties(value = {"id"}) To hide fields in response body: Static filtering
public class Product {

    //@JsonIgnore to hide this field in response body: Static filtering
    int id;

    @NotNull(message = "name can not be null")
    @Size(min = 3, message = "name must be more than 3 char long")
    String name;

    @Positive(message = "price must be a positive number")
    int price;


}
