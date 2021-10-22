package com.joinsolutions.curso_demo_spring_boot.resources;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joinsolutions.curso_demo_spring_boot.entities.Product;
import com.joinsolutions.curso_demo_spring_boot.services.ProductService;


@RestController
@RequestMapping(name = "/products")
public class ProductResource {
	
	@Autowired
	private ProductService productService;
	
	@GetMapping
	public ResponseEntity<List<Product>> findAll() {
		
		List<Product> list = productService.finAll();
		return ResponseEntity.ok().body(list);
	}
	
	
	@GetMapping(value = "/{id}")
	public ResponseEntity<Product> findById(@PathVariable Long id) {
		
		Product product = productService.findById(id);
		return ResponseEntity.ok().body(product);
	}
	

}