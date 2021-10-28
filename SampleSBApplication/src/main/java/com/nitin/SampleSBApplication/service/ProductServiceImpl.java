package com.nitin.SampleSBApplication.service;

import com.nitin.SampleSBApplication.model.Product;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

@Service
public class ProductServiceImpl implements ProductService {

    private static Map<Integer, Product> productMap = new HashMap<>();

    @Override
    public Collection<Product> getProducts() {
        return productMap.values();
    }

    @Override
    public void addProduct(Product product) {
        productMap.put(product.getId(), product);
    }

    @Override
    public void deleteProduct(int id) {
        productMap.remove(id);
    }

    @Override
    public Product getProduct(int id) {
        return productMap.get(id);
    }

    @Override
    public boolean containsProduct(int id) {
        return productMap.containsKey(id);
    }
}
