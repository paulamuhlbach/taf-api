package io.github.paulamuhlbach.tafapi.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

import io.github.paulamuhlbach.tafapi.repository.EstadoRepository;
import io.github.paulamuhlbach.tafapi.model.Estado;

@RestController
@RequestMapping("/api/estados")
public class EstadoController {
	
	@Autowired
	private EstadoRepository estadoRepository;
	
	@PostMapping
	public Estado save(@RequestBody Estado estado) {
		return estadoRepository.save(estado);
	}
	
	@GetMapping("{id}")
	public Estado getById(@PathVariable Long id) {
		return estadoRepository.findById(id).orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
		
	}

}