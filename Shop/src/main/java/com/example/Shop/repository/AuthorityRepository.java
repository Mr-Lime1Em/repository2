package com.example.Shop.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.example.Shop.entity.AuthorityEntity;

public interface AuthorityRepository extends JpaRepository<AuthorityEntity, Long>{

}