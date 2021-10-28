package com.nitin.SampleSBApplication.controller;

import com.nitin.SampleSBApplication.exception.ProductNotFoundException;
import com.nitin.SampleSBApplication.model.Product;
import com.nitin.SampleSBApplication.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
public class ProductController {

    @Autowired
    ProductService productService;

    @GetMapping(value = "/products")
    public ResponseEntity<Object> getProducts() {
        return new ResponseEntity<>(productService.getProducts(), HttpStatus.ACCEPTED);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.GET)
    public ResponseEntity<Object> getAProduct(@PathVariable int id) {
        if (!productService.containsProduct(id)) {
            throw new ProductNotFoundException("Not found bro");
        }
        return new ResponseEntity<>(
                productService.getProduct(id), HttpStatus.OK);
    }

    //@PostMapping
    @RequestMapping(value = "/products", method = RequestMethod.POST)
    public ResponseEntity<Object> addAProduct(@RequestBody Product product) {
        productService.addProduct(product);
        return new ResponseEntity<>("Product " + product.getName() + " added", HttpStatus.CREATED);
    }

    @RequestMapping(value = "/products/{id}", method = RequestMethod.DELETE)
    public void deleteAProduct(@PathVariable int id) {
        if (!productService.containsProduct(id)) {
            throw new ProductNotFoundException();
            // throw new ProductNotFoundException("Product not found for deletion");
        }
        productService.deleteProduct(id);
        return;
    }

}
