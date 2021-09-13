package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.Cidade;
import io.github.paulamuhlbach.taf.domain.service.CidadeService;
import io.github.paulamuhlbach.taf.domain.repository.CidadeRepository;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/cidades")
public class CidadeController {

    @Autowired
    private CidadeService cidadeService;

    @Autowired
    private CidadeRepository cidadeRepository;

    @GetMapping
    public List<Cidade> getCidades() {
        return cidadeService.getCidades();
    }

    @PostMapping
    public void postCidade(@RequestBody Cidade cidade) {
        cidadeService.add(cidade);
    }

    @GetMapping("/{id}")
    public Cidade getById(@PathVariable(required = true) long id) {
        return cidadeService.getCidadeById(id);
    }

    @PutMapping("/{id}")
    public Cidade updateCidade(@PathVariable Long id, @Valid @RequestBody Cidade cidadeoRequest) {
        return cidadeRepository.findById(id).map(cidade -> {
            cidade.setNome(cidadeoRequest.getNome());
            cidade.setIdEstado(cidadeoRequest.getIdEstado());
            return cidadeRepository.save(cidade);
        }).orElseThrow(() -> new ResourceNotFoundException("Id da cidade " + id + " não encontrado"));
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable(required = true) long id) {
        cidadeService.delete(id);
    }

}
