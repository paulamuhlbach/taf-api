package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.Endereco;
import io.github.paulamuhlbach.taf.domain.service.EnderecoService;
import io.github.paulamuhlbach.taf.domain.repository.EnderecoRepository;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/enderecos")
public class EnderecoController {

    @Autowired
    private EnderecoService enderecoService;

    @Autowired
    private EnderecoRepository enderecoRepository;

    @GetMapping
    public List<Endereco> getEnderecos() {
        return enderecoService.getEnderecos();
    }

    @PostMapping
    public void postEndereco(@RequestBody Endereco endereco) {
        enderecoService.add(endereco);
    }

    @GetMapping("/{id}")
    public Endereco getById(@PathVariable(required = true) long id) {
        return enderecoService.getEnderecoById(id);
    }

    @PutMapping("/{id}")
    public Endereco updateEndereco(@PathVariable Long id, @Valid @RequestBody Endereco enderecooRequest) {
        return enderecoRepository.findById(id).map(endereco -> {
            endereco.setRua(enderecooRequest.getRua());
            endereco.setNumero(enderecooRequest.getNumero());
            endereco.setComplemento(enderecooRequest.getComplemento());
            endereco.setBairro(enderecooRequest.getBairro());
            endereco.setCep(enderecooRequest.getCep());
            endereco.setIdCidade(enderecooRequest.getIdCidade());
            return enderecoRepository.save(endereco);
        }).orElseThrow(() -> new ResourceNotFoundException("Id do endereço " + id + " não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        enderecoService.delete(id);
    }

}
