package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.PageContent;
import io.github.paulamuhlbach.taf.domain.repository.PageContentRepository;
import io.github.paulamuhlbach.taf.domain.service.PageContentService;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@RequestMapping("/pageContents")
public class PageContentController {

	@Autowired
	private PageContentService pageContentService;

	@Autowired
	private PageContentRepository pageContentRepository;

	@GetMapping
	public List<PageContent> getPagesContents() {
		return pageContentService.getPagesContents();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postPageContent(@RequestBody PageContent pageContent) {
		pageContentService.add(pageContent);
	}

	@GetMapping("/{id}")
	public PageContent getById(@PathVariable(required = true) long id) {
		return pageContentService.getPageContentById(id);
	}

	@PutMapping("/{id}")
	public PageContent updatePageContent(@PathVariable Long id, @Valid @RequestBody PageContent pageContentRequest) {

		return pageContentRepository.findById(id).map(pageContent -> {

			pageContent.setIdPage(pageContentRequest.getIdPage());
			pageContent.setTitle(pageContentRequest.getTitle());
			pageContent.setContent(pageContentRequest.getContent());
			pageContent.setIdUserCreated(pageContentRequest.getIdUserCreated());
			pageContent.setIdUserModify(pageContentRequest.getIdUserModify());
			pageContent.setCreatedIn(pageContentRequest.getCreatedIn());
			pageContent.setLastModify(pageContentRequest.getLastModify());
			pageContent.setApproved(pageContentRequest.getApproved());
			pageContent.setIdUserApproved(pageContentRequest.getIdUserApproved());
			pageContent.setApprovedIn(pageContentRequest.getApprovedIn());
			pageContent.setPublishContent(pageContentRequest.getPublishContent());
			pageContent.setIdUserPublish(pageContentRequest.getIdUserPublish());
			pageContent.setPublishedIn(pageContentRequest.getPublishedIn());

			return pageContentRepository.save(pageContent);

		}).orElseThrow(() -> new ResourceNotFoundException("Id da entidade PageContent " + id + " não foi encontrado"));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		pageContentService.delete(id);
	}

}
