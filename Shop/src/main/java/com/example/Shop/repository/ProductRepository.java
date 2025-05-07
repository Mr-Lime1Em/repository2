package com.example.Shop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.Shop.entity.ProductEntity;

@Repository
public interface ProductRepository extends JpaRepository<ProductEntity, Long> {
	List<ProductEntity> findByOwnerId(Long ownerId);
	List<ProductEntity> findByBrandContainingIgnoreCase(String keyword);
	List<ProductEntity> findAllByOrderByPriceAsc();
	List<ProductEntity> findAllByOrderByPriceDesc();
	List<ProductEntity> findAllByOrderByRatingDesc();
	List<ProductEntity> findAllByRatingGreaterThanEqual(Integer minRating);
}
