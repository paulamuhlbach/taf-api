package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.ContentUserRole;
import io.github.paulamuhlbach.taf.domain.repository.ContentUserRoleRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class ContentUserRoleService {

    @Autowired
    private ContentUserRoleRepository repository;

    public void add(ContentUserRole contentUserRole) {
        repository.save(toEntity(contentUserRole));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<ContentUserRole> getContentUserRoles() {
        return (List<ContentUserRole>) repository.findAll();
    }

    public ContentUserRole getContentUserRoleById(long id) {
        Optional<ContentUserRole> optionalContentUserRole = repository.findById(id);
        return optionalContentUserRole.orElseThrow(
                () -> new ResourceNotFoundException("O id da entity contentUserRole " + id + " não foi encontrado"));
    }

    private ContentUserRole toEntity(ContentUserRole contentUserRole) {
        ContentUserRole entity = new ContentUserRole();
        entity.setDescription(contentUserRole.getDescription());
        entity.setActive(contentUserRole.getActive());
        return entity;
    }
}
