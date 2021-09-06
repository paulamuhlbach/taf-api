package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.Instituicao;
import io.github.paulamuhlbach.taf.domain.repository.InstituicaoRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class InstituicaoService {

    @Autowired
    private InstituicaoRepository repository;

    public void add(Instituicao instituicao) {
        repository.save(toEntity(instituicao));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Instituicao> getInstituicoes() {
        return (List<Instituicao>) repository.findAll();
    }

    public Instituicao getInstituicaoById(long id) {
        Optional<Instituicao> optionalInstituicao = repository.findById(id);
        return optionalInstituicao.orElseThrow(
                () -> new ResourceNotFoundException("O id da entity instituicao " + id + " não foi encontrado"));
    }

    private Instituicao toEntity(Instituicao instituicao) {
        Instituicao entity = new Instituicao();
        entity.setNome(instituicao.getNome());
        entity.setIdTipoInstituicao(instituicao.getIdTipoInstituicao());
        entity.setIdEndereco(instituicao.getIdEndereco());
        entity.setCnpj(instituicao.getCnpj());
        return entity;
    }
}
