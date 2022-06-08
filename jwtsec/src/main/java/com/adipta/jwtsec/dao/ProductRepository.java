package com.adipta.jwtsec.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.adipta.jwtsec.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> 
{

}