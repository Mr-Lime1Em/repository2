package com.example.Shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Shop.entity.ProductEntity;
import com.example.Shop.repository.ProductRepository;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public void addProduct(ProductEntity product) {
        productRepository.save(product);
    }

    public void deleteProduct(Long id) {
        productRepository.deleteById(id);
    }

    public List<ProductEntity> getAllProducts() {
        return productRepository.findAll();
    }

    public Optional<ProductEntity> getProductById(Long id) {
        return productRepository.findById(id);
    }

    public List<ProductEntity> searchProducts(String keyword) {
        return productRepository.findByBrandContainingIgnoreCase(keyword);
    }
    
    public List<ProductEntity> filterByMinRating(Integer minRating){
        return productRepository.findAllByRatingGreaterThanEqual(minRating);
    }
    
    public List<ProductEntity> sortByPriceAsc() {
        return productRepository.findAllByOrderByPriceAsc();
    }

    public List<ProductEntity> sortByPriceDesc() {
        return productRepository.findAllByOrderByPriceDesc();
    }
    
    public void uptadeCar(Long id, ProductEntity productEntity) {
        Optional<ProductEntity> oldCar = productRepository.findById(id);
        if (oldCar.isPresent()) {
            ProductEntity car = oldCar.get();
            car.setBrand(productEntity.getBrand());
            car.setType(productEntity.getType());
            car.setCategory(productEntity.getCategory());
            car.setDescription(productEntity.getDescription());
            car.setPrice(productEntity.getPrice());
            car.setImgUrl(productEntity.getImgUrl());
            car.setRating(productEntity.getRating());
            car.setOwnerId(productEntity.getOwnerId());
            productRepository.save(car);
        }
    }
}