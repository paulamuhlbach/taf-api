package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.MenuGroup;
import io.github.paulamuhlbach.taf.domain.repository.MenuGroupRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MenuGroupService {

    @Autowired
    private MenuGroupRepository repository;

    public void add(MenuGroup menuGroup) {
        repository.save(toEntity(menuGroup));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<MenuGroup> getMenuGroups() {
        return (List<MenuGroup>) repository.findAll();
    }

    public MenuGroup getMenuGroupById(long id) {
        Optional<MenuGroup> optionalMenuGroup = repository.findById(id);
        return optionalMenuGroup.orElseThrow(
                () -> new ResourceNotFoundException("O id da entity menuGroup " + id + " não foi encontrado"));
    }

    private MenuGroup toEntity(MenuGroup menuGroup) {

        MenuGroup entity = new MenuGroup();
        entity.setGroupName(menuGroup.getGroupName());
        entity.setDescription(menuGroup.getDescription());
        entity.setLevel(menuGroup.getLevel());
        entity.setIdSubmenuGroup(menuGroup.getIdSubmenuGroup());
        entity.setIdUserCreated(menuGroup.getIdUserCreated());
        entity.setIdUserModify(menuGroup.getIdUserModify());
        entity.setIdIcon(menuGroup.getIdIcon());
        entity.setCreatedIn(menuGroup.getCreatedIn());
        entity.setLastModify(menuGroup.getLastModify());
        entity.setActive(menuGroup.getActive());
        return entity;
    }
}
