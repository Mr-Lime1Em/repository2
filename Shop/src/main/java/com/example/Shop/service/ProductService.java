package com.example.Shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.Shop.entity.ProductEntity;
import com.example.Shop.entity.UserEntity;
import com.example.Shop.jwt.SecurityUtil;
import com.example.Shop.repository.ProductRepository;
import com.example.Shop.repository.UserRepository;
import com.example.Shop.request.ProductRequest;

@Service
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    @Autowired
    private UserRepository userRepository;

    public void addProductForCurrentUser(ProductRequest productRequest) {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
        ProductEntity productEntity = new ProductEntity();
        productEntity.setBrand(productRequest.getBrand());
        productEntity.setType(productRequest.getType());
        productEntity.setCategory(productRequest.getCategory());
        productEntity.setDescription(productRequest.getDescription());
        productEntity.setPrice(productRequest.getPrice());
        productEntity.setImgUrl(productRequest.getImgUrl());
        productEntity.setRating(productRequest.getRating());
        productEntity.setOwnerId(userEntity.getId());
        productRepository.save(productEntity);
    }
    
    public List<ProductEntity> getMyProducts() {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
        return productRepository.findByOwnerId(userEntity.getId());
    }

    public ProductEntity getMyProductById(Long id) {
        String username = SecurityUtil.getCurrentUsername();
        ProductEntity productEntity = productRepository.findById(id).orElseThrow(() -> new RuntimeException());
        Long currentUserId = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException()).getId();

        if (!productEntity.getOwnerId().equals(currentUserId)) {
            throw new RuntimeException("");
        }
        return productEntity;
    }
    
    public void deleteProduct(Long id) {
    	ProductEntity productEntity = getMyProductById(id);
        productRepository.delete(productEntity);
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
    	String username = SecurityUtil.getCurrentUsername();
        UserEntity userEntity = userRepository.findByUsername(username).orElseThrow(() -> new RuntimeException());
        Optional<ProductEntity> oldCar = Optional.ofNullable(getMyProductById(id));
        if (oldCar.isPresent()) {
            ProductEntity car = oldCar.get();
            car.setBrand(productEntity.getBrand());
            car.setType(productEntity.getType());
            car.setCategory(productEntity.getCategory());
            car.setDescription(productEntity.getDescription());
            car.setPrice(productEntity.getPrice());
            car.setImgUrl(productEntity.getImgUrl());
            car.setRating(productEntity.getRating());
            car.setOwnerId(userEntity.getId());
            productRepository.save(car);
        }
    }
}