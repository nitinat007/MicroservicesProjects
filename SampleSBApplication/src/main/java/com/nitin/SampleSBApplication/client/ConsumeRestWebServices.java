package com.nitin.SampleSBApplication.client;

import com.nitin.SampleSBApplication.model.Product;
import com.nitin.SampleSBApplication.template.SampleRestTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.Arrays;

/*
To consume an API −
Autowired the Rest Template Object.
Use HttpHeaders to set the Request Headers.
Use HttpEntity to wrap the request object.
Provide the URL, HttpMethod, and Return type for Exchange() method.
 */
@RestController
public class ConsumeRestWebServices {
    @Autowired
    SampleRestTemplate restTemplate;

    @RequestMapping(value = "/consume/products", method = RequestMethod.GET)
    public String getProducts() {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<String> httpEntity = new HttpEntity<String>(headers);

        return restTemplate.getRestTemplate().exchange("http://localhost:8081/products", HttpMethod.GET, httpEntity, String.class).getBody();
    }

    @RequestMapping(value = "/consume/products", method = RequestMethod.POST)
    public String addProduct(@RequestBody Product product) {
        HttpHeaders headers = new HttpHeaders();
        headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        HttpEntity<Product> httpEntity = new HttpEntity<Product>(product, headers);
        return restTemplate.getRestTemplate().exchange("http://localhost:8081/products", HttpMethod.POST, httpEntity, String.class).getBody();
    }

}
