package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.PageType;
import io.github.paulamuhlbach.taf.domain.repository.PageTypeRepository;
import io.github.paulamuhlbach.taf.domain.service.PageTypeService;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/pageTypes")
public class PageTypeController {

	@Autowired
	private PageTypeService pageTypeService;

	@Autowired
	private PageTypeRepository pageTypeRepository;

	@GetMapping
	public List<PageType> getPagesTypes() {
		return pageTypeService.getPagesTypes();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postPageType(@RequestBody PageType pageType) {
		pageTypeService.add(pageType);
	}

	@GetMapping("/{id}")
	public PageType getById(@PathVariable(required = true) long id) {
		return pageTypeService.getPageTypeById(id);
	}

	@PutMapping("/{id}")
	public PageType updatePageType(@PathVariable Long id, @Valid @RequestBody PageType pageTypeRequest) {

		return pageTypeRepository.findById(id).map(pageType -> {

			pageType.setDescription(pageTypeRequest.getDescription());
			pageType.setIdUserCreated(pageTypeRequest.getIdUserCreated());
			pageType.setIdUserModify(pageTypeRequest.getIdUserModify());
			pageType.setCreatedIn(pageTypeRequest.getCreatedIn());
			pageType.setLastModify(pageTypeRequest.getLastModify());
			pageType.setActive(pageTypeRequest.getActive());

			return pageTypeRepository.save(pageType);

		}).orElseThrow(() -> new ResourceNotFoundException("Id da entidade PageType " + id + " não foi encontrado"));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		pageTypeService.delete(id);
	}

}
