package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.TipoInstituicao;
import io.github.paulamuhlbach.taf.domain.repository.TipoInstituicaoRepository;
import io.github.paulamuhlbach.taf.domain.service.TipoInstituicaoService;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@RequestMapping("/tiposInstituicoes")
public class TipoInstituicaoController {

	@Autowired
	private TipoInstituicaoService tipoInstituicaoService;

	@Autowired
	private TipoInstituicaoRepository tipoInstituicaoRepository;

	@GetMapping
	public List<TipoInstituicao> getTipoInstituicoes() {
		return tipoInstituicaoService.getTipoInstituicoes();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postTipoInstituicao(@RequestBody TipoInstituicao tipoInstituicao) {
		tipoInstituicaoService.add(tipoInstituicao);
	}

	@GetMapping("/{id}")
	public TipoInstituicao getById(@PathVariable(required = true) long id) {
		return tipoInstituicaoService.getTipoInstituicaoById(id);
	}

	@PutMapping("/{id}")
	public TipoInstituicao updateTipoInstituicao(@PathVariable Long id,
			@Valid @RequestBody TipoInstituicao tipoInstituicaooRequest) {
		return tipoInstituicaoRepository.findById(id).map(tipoInstituicao -> {
			tipoInstituicao.setDescricao(tipoInstituicaooRequest.getDescricao());
			return tipoInstituicaoRepository.save(tipoInstituicao);

		}).orElseThrow(
				() -> new ResourceNotFoundException("Id da entidade TipoInstituicao " + id + " não foi encontrado"));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		tipoInstituicaoService.delete(id);
	}

}
