package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.Menu;
import io.github.paulamuhlbach.taf.domain.repository.MenuRepository;
import io.github.paulamuhlbach.taf.domain.service.MenuService;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/menus")
public class MenuController {

	@Autowired
	private MenuService menuService;

	@Autowired
	private MenuRepository menuRepository;

	@GetMapping
	public List<Menu> getMenus() {
		return menuService.getMenus();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postMenu(@RequestBody Menu menu) {
		menuService.add(menu);
	}

	@GetMapping("/{id}")
	public Menu getById(@PathVariable(required = true) long id) {
		return menuService.getMenuById(id);
	}

	@PutMapping("/{id}")
	public Menu updateMenu(@PathVariable Long id, @Valid @RequestBody Menu menuRequest) {

		return menuRepository.findById(id).map(menu -> {

			menu.setIdMenuGroup(menuRequest.getIdMenuGroup());
			menu.setIdSubGroupMenu(menuRequest.getIdSubGroupMenu());
			menu.setName(menuRequest.getName());
			menu.setDescription(menuRequest.getDescription());
			menu.setLevel(menuRequest.getLevel());
			menu.setIdIcon(menuRequest.getIdIcon());
			menu.setIdUserCreated(menuRequest.getIdUserCreated());
			menu.setIdUserModify(menuRequest.getIdUserModify());
			menu.setCreatedIn(menuRequest.getCreatedIn());
			menu.setLastModify(menuRequest.getLastModify());
			menu.setActive(menuRequest.getActive());

			return menuRepository.save(menu);

		}).orElseThrow(() -> new ResourceNotFoundException("Id da entidade Menu " + id + " não foi encontrado"));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		menuService.delete(id);
	}

}
