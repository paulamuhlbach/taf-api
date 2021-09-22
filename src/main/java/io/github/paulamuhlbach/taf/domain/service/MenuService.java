package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.Menu;
import io.github.paulamuhlbach.taf.domain.repository.MenuRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class MenuService {

    @Autowired
    private MenuRepository repository;

    public void add(Menu menu) {
        repository.save(toEntity(menu));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<Menu> getMenus() {
        return (List<Menu>) repository.findAll();
    }

    public Menu getMenuById(long id) {
        Optional<Menu> optionalMenu = repository.findById(id);
        return optionalMenu
                .orElseThrow(() -> new ResourceNotFoundException("O id da entity menu " + id + " não foi encontrado"));
    }

    private Menu toEntity(Menu menu) {
        Menu entity = new Menu();
        entity.setIdMenuGroup(menu.getIdMenuGroup());
        entity.setIdSubGroupMenu(menu.getIdSubGroupMenu());
        entity.setName(menu.getName());
        entity.setDescription(menu.getDescription());
        entity.setLevel(menu.getLevel());
        entity.setIdIcon(menu.getIdIcon());
        entity.setIdUserCreated(menu.getIdUserCreated());
        entity.setIdUserModify(menu.getIdUserModify());
        entity.setCreatedIn(menu.getCreatedIn());
        entity.setLastModify(menu.getLastModify());
        entity.setActive(menu.getActive());
        return entity;
    }
}
