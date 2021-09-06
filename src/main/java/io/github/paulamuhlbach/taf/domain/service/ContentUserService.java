package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.ContentUser;
import io.github.paulamuhlbach.taf.domain.repository.ContentUserRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContentUserService {

    @Autowired
    private ContentUserRepository repository;

    public void add(ContentUser contentUser) {
        repository.save(toEntity(contentUser));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<ContentUser> getContentUsers() {
        return (List<ContentUser>) repository.findAll();
    }

    public ContentUser getContentUserById(long id) {
        Optional<ContentUser> optionalContentUser = repository.findById(id);
        return optionalContentUser.orElseThrow(
                () -> new ResourceNotFoundException("O id da entity contentUser " + id + " não foi encontrado"));
    }

    private ContentUser toEntity(ContentUser contentUser) {
        ContentUser entity = new ContentUser();
        entity.setIdContentUserRole(contentUser.getIdContentUserRole());
        entity.setIdUserProfile(contentUser.getIdUserProfile());
        entity.setCreatedIn(contentUser.getCreatedIn());
        entity.setLastModify(contentUser.getLastModify());
        entity.setActive(contentUser.getActive());
        return entity;
    }
}
