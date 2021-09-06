package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.TipoInstituicao;
import io.github.paulamuhlbach.taf.domain.repository.TipoInstituicaoRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class TipoInstituicaoService {

    @Autowired
    private TipoInstituicaoRepository repository;

    public void add(TipoInstituicao tipoInstituicao) {
        repository.save(toEntity(tipoInstituicao));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<TipoInstituicao> getTipoInstituicoes() {
        return (List<TipoInstituicao>) repository.findAll();
    }

    public TipoInstituicao getTipoInstituicaoById(long id) {
        Optional<TipoInstituicao> optionalTipoInstituicao = repository.findById(id);
        return optionalTipoInstituicao.orElseThrow(
                () -> new ResourceNotFoundException("O id da entity tipoInstituicao " + id + " não foi encontrado"));
    }

    private TipoInstituicao toEntity(TipoInstituicao tipoInstituicao) {

        TipoInstituicao entity = new TipoInstituicao();
        entity.setDescricao(tipoInstituicao.getDescricao());

        return entity;
    }
}
