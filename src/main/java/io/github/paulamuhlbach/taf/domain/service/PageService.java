package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.Page;
import io.github.paulamuhlbach.taf.domain.repository.PageRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PageService {

    @Autowired
    private PageRepository repository;

    public void add(Page page) {
        repository.save(toEntity(page));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Page> getPages() {
        return (List<Page>) repository.findAll();
    }

    public Page getPageById(long id) {
        Optional<Page> optionalPage = repository.findById(id);
        return optionalPage
                .orElseThrow(() -> new ResourceNotFoundException("O id da entity page " + id + " não foi encontrado"));
    }

    private Page toEntity(Page page) {

        Page entity = new Page();
        entity.setIdPageType(page.getIdPageType());
        entity.setTitle(page.getTitle());
        entity.setSlug(page.getSlug());
        entity.setUrl(page.getUrl());
        entity.setIdMenu(page.getIdMenu());
        entity.setDescription(page.getDescription());
        entity.setIdUserCreated(page.getIdUserCreated());
        entity.setIdUserModify(page.getIdUserModify());
        entity.setCreatedIn(page.getCreatedIn());
        entity.setLastModify(page.getLastModify());
        entity.setActive(page.getActive());

        return entity;
    }
}
