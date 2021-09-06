package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.PageContent;
import io.github.paulamuhlbach.taf.domain.repository.PageContentRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class PageContentService {

    @Autowired
    private PageContentRepository repository;

    public void add(PageContent pageContent) {
        repository.save(toEntity(pageContent));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<PageContent> getPagesContents() {
        return (List<PageContent>) repository.findAll();
    }

    public PageContent getPageContentById(long id) {
        Optional<PageContent> optionalPage = repository.findById(id);
        return optionalPage.orElseThrow(
                () -> new ResourceNotFoundException("O id da entity pageContent " + id + " não foi encontrado"));
    }

    private PageContent toEntity(PageContent pageContent) {

        PageContent entity = new PageContent();
        entity.setIdPage(pageContent.getIdPage());
        entity.setTitle(pageContent.getTitle());
        entity.setContent(pageContent.getContent());
        entity.setIdUserCreated(pageContent.getIdUserCreated());
        entity.setIdUserModify(pageContent.getIdUserModify());
        entity.setCreatedIn(pageContent.getCreatedIn());
        entity.setLastModify(pageContent.getLastModify());
        entity.setApproved(pageContent.getApproved());
        entity.setIdUserApproved(pageContent.getIdUserApproved());
        entity.setApprovedIn(pageContent.getApprovedIn());
        entity.setPublishContent(pageContent.getPublishContent());
        entity.setIdUserPublish(pageContent.getIdUserPublish());
        entity.setPublishedIn(pageContent.getPublishedIn());

        return entity;
    }
}
