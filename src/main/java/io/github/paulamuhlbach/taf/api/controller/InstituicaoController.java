package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.Instituicao;
import io.github.paulamuhlbach.taf.domain.repository.InstituicaoRepository;
import io.github.paulamuhlbach.taf.domain.service.InstituicaoService;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@RequestMapping("/instituicoes")
public class InstituicaoController {

	@Autowired
	private InstituicaoService instituicaoService;

	@Autowired
	private InstituicaoRepository instituicaoRepository;

	@GetMapping
	public List<Instituicao> getInstituicoes() {
		return instituicaoService.getInstituicoes();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postInstituicao(@RequestBody Instituicao instituicao) {
		instituicaoService.add(instituicao);
	}

	@GetMapping("/{id}")
	public Instituicao getById(@PathVariable(required = true) long id) {
		return instituicaoService.getInstituicaoById(id);
	}

	@PutMapping("/{id}")
	public Instituicao updateInstituicao(@PathVariable Long id, @Valid @RequestBody Instituicao instituicaoRequest) {
		return instituicaoRepository.findById(id).map(instituicao -> {
			instituicao.setNome(instituicaoRequest.getNome());
			instituicao.setIdTipoInstituicao(instituicaoRequest.getIdTipoInstituicao());
			instituicao.setIdEndereco(instituicaoRequest.getIdEndereco());
			instituicao.setCnpj(instituicaoRequest.getCnpj());
			return instituicaoRepository.save(instituicao);

		}).orElseThrow(() -> new ResourceNotFoundException("Id da entidade Instituicao " + id + " não foi encontrado"));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		instituicaoService.delete(id);
	}

}
