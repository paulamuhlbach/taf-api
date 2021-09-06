package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.PageType;
import io.github.paulamuhlbach.taf.domain.repository.PageTypeRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PageTypeService {

    @Autowired
    private PageTypeRepository repository;

    public void add(PageType pageType) {
        repository.save(toEntity(pageType));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<PageType> getPagesTypes() {
        return (List<PageType>) repository.findAll();
    }

    public PageType getPageTypeById(long id) {
        Optional<PageType> optionalPage = repository.findById(id);
        return optionalPage.orElseThrow(
                () -> new ResourceNotFoundException("O id da entity pageType " + id + " não foi encontrado"));
    }

    private PageType toEntity(PageType pageType) {

        PageType entity = new PageType();
        entity.setDescription(pageType.getDescription());
        entity.setIdUserCreated(pageType.getIdUserCreated());
        entity.setIdUserModify(pageType.getIdUserModify());
        entity.setCreatedIn(pageType.getCreatedIn());
        entity.setLastModify(pageType.getLastModify());
        entity.setActive(pageType.getActive());

        return entity;
    }
}
