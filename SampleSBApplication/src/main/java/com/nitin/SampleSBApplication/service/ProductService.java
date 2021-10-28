package com.nitin.SampleSBApplication.service;

import com.nitin.SampleSBApplication.model.Product;

import java.util.Collection;

public interface ProductService {

    public Collection<Product> getProducts();

    public void addProduct(Product product);

    public void deleteProduct(int id);

    public Product getProduct(int id);

    public boolean containsProduct(int id);

}
