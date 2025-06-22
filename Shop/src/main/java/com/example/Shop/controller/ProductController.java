package com.example.Shop.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.example.Shop.entity.ProductEntity;
import com.example.Shop.request.ProductRequest;
import com.example.Shop.service.ProductService;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/products")
@CrossOrigin("*")
public class ProductController {

    @Autowired
    private ProductService productService;

    @GetMapping("/my-cars")
    public List<ProductEntity> getAllProducts() {
        return productService.getMyProducts();
    }
    
    @GetMapping
    public List<ProductEntity> getMyProducts() {
        return productService.getAllProducts();
    }

    @GetMapping("/{id}")
    public Optional<ProductEntity> getProduct(@PathVariable Long id) {
        return productService.getProductById(id);
    }

    @GetMapping("/search")
    public List<ProductEntity> searchProducts(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }

    @PostMapping("/add")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void addProduct(@RequestBody @Valid ProductRequest carEntity) {
        productService.addProductForCurrentUser(carEntity);
    }

    @DeleteMapping("delete/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void deleteProduct(@PathVariable Long id) {
        productService.deleteProduct(id);
    }
    
    @GetMapping("/rating")
    public List<ProductEntity> byRating(@RequestParam Integer minRating){
        return productService.filterByMinRating(minRating);
    }
    
    @GetMapping("/sort-by-price-asc")
    public List<ProductEntity> byPriceAsc() {
        return productService.sortByPriceAsc();
    }

    @GetMapping("/sort-by-price-desc")
    public List<ProductEntity> byPriceDesc() {
        return productService.sortByPriceDesc();
    }
    
    @PutMapping("/update/{id}")
    @PreAuthorize("hasRole('ROLE_USER')")
    public void updateCar(@PathVariable Long id, @RequestBody @Valid ProductEntity car) {
        productService.uptadeCar(id, car);
    }
}