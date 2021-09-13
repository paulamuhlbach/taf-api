package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.Estado;
import io.github.paulamuhlbach.taf.domain.repository.EstadoRepository;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@CrossOrigin("http://localhost:4200")
public class EstadoController {

    @Autowired
    private EstadoRepository estadoRepository;

    @GetMapping("/api/estados")
    public List<Estado> listar() {
        return estadoRepository.findAll();
    }

    @GetMapping("/api/estados/{estadoId}")
    public ResponseEntity<Estado> buscar(@PathVariable Long estadoId) {
        Optional<Estado> estado = estadoRepository.findById(estadoId);

        if (estado.isPresent()) {
            return ResponseEntity.ok(estado.get());
        }

        return ResponseEntity.notFound().build();
    }

    @PostMapping("/api/estados")
    public Estado creatEstado(@Valid @RequestBody Estado estado) {
        return estadoRepository.save(estado);
    }

    @PutMapping("/api/estados/{estadoId}")
    public Estado updateEstado(@PathVariable Long estadoId, @Valid @RequestBody Estado estadoRequest) {
        return estadoRepository.findById(estadoId).map(estado -> {
            estado.setNome(estadoRequest.getNome());
            estado.setSigla(estadoRequest.getSigla());
            return estadoRepository.save(estado);
        }).orElseThrow(() -> new ResourceNotFoundException("estadoId " + estadoId + " não encontrado"));
    }

    @DeleteMapping("/api/estados/{estadoId}")
    public ResponseEntity<?> deleteEstado(@PathVariable Long estadoId) {
        return estadoRepository.findById(estadoId).map(estado -> {
            estadoRepository.delete(estado);
            return ResponseEntity.ok().build();
        }).orElseThrow(() -> new ResourceNotFoundException("EstadoId " + estadoId + " não encontrado"));
    }
}
