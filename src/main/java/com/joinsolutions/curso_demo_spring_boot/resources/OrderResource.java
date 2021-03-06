package com.joinsolutions.curso_demo_spring_boot.resources;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.joinsolutions.curso_demo_spring_boot.entities.Order;
import com.joinsolutions.curso_demo_spring_boot.services.OrderService;

@RestController
@RequestMapping("/orders")
public class OrderResource {

	@Autowired
	private OrderService orderService;

	@GetMapping
	public ResponseEntity<List<Order>> findAll() {

		List<Order> list = orderService.findAll();
		return ResponseEntity.ok().body(list);
	}
	
	@GetMapping(value = "/{id}")	
	public ResponseEntity<Order> findById(@PathVariable Long id ) {

		Order obj = orderService.findById(id);
		return ResponseEntity.ok().body(obj);
	}
	
	@PostMapping
	public ResponseEntity<Order> insert(@RequestBody Order obj) {
		obj = orderService.insert(obj);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).body(obj);
	}

	@DeleteMapping(value = "/{id}")
	public ResponseEntity<Void> deleteById(@PathVariable Long id) {
		orderService.deleteById(id);
		return ResponseEntity.noContent().build();
	}

	@PutMapping(value = "/{id}")
	public ResponseEntity<Order> update(@PathVariable Long id, @RequestBody Order obj) {
		obj = orderService.update(id, obj);
		URI uri = ServletUriComponentsBuilder
				.fromCurrentRequest()
				.path("/{id}")
				.buildAndExpand(obj.getId())
				.toUri();
		return ResponseEntity.created(uri).body(obj);

	}

}
