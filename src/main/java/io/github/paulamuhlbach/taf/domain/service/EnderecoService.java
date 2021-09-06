package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.Endereco;
import io.github.paulamuhlbach.taf.domain.repository.EnderecoRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class EnderecoService {

    @Autowired
    private EnderecoRepository repository;

    public void add(Endereco endereco) {
        repository.save(toEntity(endereco));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Endereco> getEnderecos() {
        return (List<Endereco>) repository.findAll();
    }

    public Endereco getEnderecoById(long id) {
        Optional<Endereco> optionalEndereco = repository.findById(id);
        return optionalEndereco.orElseThrow(
                () -> new ResourceNotFoundException("O id da entity endereço " + id + " não foi encontrado"));
    }

    private Endereco toEntity(Endereco endereco) {
        Endereco entity = new Endereco();
        entity.setRua(endereco.getRua());
        entity.setNumero(endereco.getNumero());
        entity.setComplemento(endereco.getComplemento());
        entity.setBairro(endereco.getBairro());
        entity.setCep(endereco.getCep());
        entity.setIdCidade(endereco.getIdCidade());
        return entity;
    }
}
