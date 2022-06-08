package com.adipta.jwtsec.controller;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.adipta.jwtsec.dao.ProductRepository;
import com.adipta.jwtsec.entity.Product;

@RestController
@RequestMapping(path = {"/products"})
public class ProductApi 
{
	@Autowired
	ProductRepository productRepository;
	
	@GetMapping
	public List<Product> list()
	{
		return productRepository.findAll();
	}
	
	@PostMapping
	public ResponseEntity<Product> create(@RequestBody Product product)
	{
		Product saveProduct=productRepository.save(product);
		
		URI productUri=URI.create("/products/"+saveProduct.getId());
		
		return ResponseEntity.created(productUri).body(saveProduct);
	}
}
