package org.ironhack.tue_2105.controller;

import org.ironhack.tue_2105.model.Product;
import org.ironhack.tue_2105.repository.ProductRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@RestController
@RequestMapping("/products")
public class ProductController {

    @Autowired
    private ProductRepository productRepository;

    @GetMapping
    public List<Product> getProducts(@RequestParam(required = false) Double price) {
        if (price != null) {
            return productRepository.findByPriceGreaterThan(price);
        }
        return productRepository.findAll();
    }

    @GetMapping("/{id}")
    public Product getProductById(@PathVariable Long id) {
        return productRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Product not found"));
    }

    @GetMapping("/name/{name}")
    public List<Product> getProductByName(@PathVariable String name) {
        return productRepository.findByName(name);
    }
}
