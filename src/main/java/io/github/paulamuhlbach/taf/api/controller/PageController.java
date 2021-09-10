package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.Page;
import io.github.paulamuhlbach.taf.domain.repository.PageRepository;
import io.github.paulamuhlbach.taf.domain.service.PageService;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@RequestMapping("/pages")
public class PageController {

	@Autowired
	private PageService pageService;

	@Autowired
	private PageRepository pageRepository;

	@GetMapping
	public List<Page> getPages() {
		return pageService.getPages();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postPage(@RequestBody Page page) {
		pageService.add(page);
	}

	@GetMapping("/{id}")
	public Page getById(@PathVariable(required = true) long id) {
		return pageService.getPageById(id);
	}

	@PutMapping("/{id}")
	public Page updatePage(@PathVariable Long id, @Valid @RequestBody Page pageRequest) {

		return pageRepository.findById(id).map(page -> {

			page.setIdPageType(pageRequest.getIdPageType());
			page.setTitle(pageRequest.getTitle());
			page.setSlug(pageRequest.getSlug());
			page.setUrl(pageRequest.getUrl());
			page.setIdMenu(pageRequest.getIdMenu());
			page.setIdIcon(pageRequest.getIdIcon());
			page.setDescription(pageRequest.getDescription());
			page.setIdUserCreated(pageRequest.getIdUserCreated());
			page.setIdUserModify(pageRequest.getIdUserModify());
			page.setCreatedIn(pageRequest.getCreatedIn());
			page.setLastModify(pageRequest.getLastModify());
			page.setActive(pageRequest.getActive());

			return pageRepository.save(page);

		}).orElseThrow(() -> new ResourceNotFoundException("Id da entidade Page " + id + " não foi encontrado"));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		pageService.delete(id);
	}

}
