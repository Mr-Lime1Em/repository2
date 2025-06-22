package com.example.Shop.repository;

import java.util.List;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Shop.entity.BasketEntity;

public interface BasketRepository extends JpaRepository<BasketEntity, Long> {
    List<BasketEntity> findByUserId(Long userId);
    Optional<BasketEntity> findByUserIdAndProductId(Long userId, Long carId);
    void deleteByUserIdAndProductId(Long userId, Long carId);
}