package com.fpoly.controller;

import com.fpoly.entity.Product;
import com.fpoly.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/products")
@CrossOrigin("*")
public class ProductRestApi {

    @Autowired
    private ProductService productService;

    @GetMapping
    public ResponseEntity<List<Product>> getAll() {
        return ResponseEntity.ok(productService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Product> getOne(@PathVariable("id") Long id) {
        Product product = productService.findById(id);
        if (product == null) {
            return ResponseEntity.notFound().build();
        }
        return ResponseEntity.ok(product);
    }

    @PostMapping
    public ResponseEntity<Product> create(@RequestBody Product product) {
        // Lưu ý: VueJS cần gửi kèm thuộc tính category: { id: ? } trong JSON body
        Product savedProduct = productService.save(product);
        return new ResponseEntity<>(savedProduct, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Product> update(@PathVariable("id") Long id, @RequestBody Product product) {
        Product existing = productService.findById(id);
        if (existing == null) {
            return ResponseEntity.notFound().build();
        }
        product.setId(id);
        return ResponseEntity.ok(productService.save(product));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable("id") Long id) {
        if (productService.findById(id) == null) {
            return ResponseEntity.notFound().build();
        }
        productService.deleteById(id);
        return ResponseEntity.ok().build();
    }
}