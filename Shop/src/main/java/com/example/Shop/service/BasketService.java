package com.example.Shop.service;

import org.springframework.stereotype.Service;

import com.example.Shop.entity.BasketEntity;
import com.example.Shop.entity.ProductEntity;
import com.example.Shop.entity.UserEntity;
import com.example.Shop.jwt.SecurityUtil;
import com.example.Shop.repository.BasketRepository;
import com.example.Shop.repository.ProductRepository;
import com.example.Shop.repository.UserRepository;
import com.example.Shop.response.BasketResponse;

import jakarta.transaction.Transactional;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;


@Service
public class BasketService {

    @Autowired
    private BasketRepository basketRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private ProductRepository productRepository;

    public void addToBasket(Long carId) {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        BasketEntity basket = basketRepository.findByUserIdAndProductId(user.getId(), carId).orElse(new BasketEntity());

        basket.setUserId(user.getId());
        basket.setProductId(carId);

        if (basket.getQuantity() == null) {
            basket.setQuantity(1);
        } else {
            basket.setQuantity(basket.getQuantity() + 1);
        }
        basketRepository.save(basket);
    }
    
    @Transactional
    public void removeFromBasket (Long carId) {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        basketRepository.deleteByUserIdAndProductId(user.getId(), carId);
    }

    public List<BasketResponse> getMyBasket() {
        String username = SecurityUtil.getCurrentUsername();
        UserEntity user = userRepository.findByUsername(username).orElseThrow();
        List<BasketEntity> baskets = basketRepository.findByUserId(user.getId());
        List<BasketResponse> responses = baskets.stream().map(basket -> {
            ProductEntity carEntity = productRepository.findById(basket.getProductId()).orElse(null);
            BasketResponse basketResponse = new BasketResponse();
            basketResponse.setId(carEntity.getId());
            basketResponse.setBrand(carEntity.getBrand());
            basketResponse.setType(carEntity.getType());
            basketResponse.setCategory(carEntity.getCategory());
            basketResponse.setDescription(carEntity.getDescription());
            basketResponse.setPrice(carEntity.getPrice());
            basketResponse.setRating(carEntity.getRating());
            basketResponse.setImgUrl(carEntity.getImgUrl());
            basketResponse.setRating(carEntity.getRating());
            basketResponse.setOwnerId(carEntity.getOwnerId());
            basketResponse.setQuantity(basket.getQuantity());
            return basketResponse;
        }).collect(Collectors.toList());
        return responses;
    }
}