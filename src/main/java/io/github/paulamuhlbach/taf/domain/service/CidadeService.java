package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.Cidade;
import io.github.paulamuhlbach.taf.domain.repository.CidadeRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class CidadeService {

    @Autowired
    private CidadeRepository repository;

    public void add(Cidade cidade) {
        repository.save(toEntity(cidade));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Cidade> getCidades() {
        return (List<Cidade>) repository.findAll();
    }

    public Cidade getCidadeById(long id) {
        Optional<Cidade> optionalCidade = repository.findById(id);
        return optionalCidade.orElseThrow(
                () -> new ResourceNotFoundException("O id da entity cidade " + id + " não foi encontrado"));
    }

    private Cidade toEntity(Cidade cidade) {
        Cidade entity = new Cidade();
        entity.setNome(cidade.getNome());
        entity.setIdEstado(cidade.getIdEstado());
        return entity;
    }
}
