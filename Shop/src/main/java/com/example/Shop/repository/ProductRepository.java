package com.example.Shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Shop.entity.ProductEntity;

public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	List<ProductEntity> findByOwnerId(Long ownerId);
	List<ProductEntity> findByBrandContainingIgnoreCase(String keyword);
}
