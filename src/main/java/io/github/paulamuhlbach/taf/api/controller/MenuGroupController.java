package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.MenuGroup;
import io.github.paulamuhlbach.taf.domain.repository.MenuGroupRepository;
import io.github.paulamuhlbach.taf.domain.service.MenuGroupService;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/menuGroups")
public class MenuGroupController {

	@Autowired
	private MenuGroupService menuGroupService;

	@Autowired
	private MenuGroupRepository menuGroupRepository;

	@GetMapping
	public List<MenuGroup> getMenuGroups() {
		return menuGroupService.getMenuGroups();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postMenuGroup(@RequestBody MenuGroup menuGroup) {
		menuGroupService.add(menuGroup);
	}

	@GetMapping("/{id}")
	public MenuGroup getById(@PathVariable(required = true) long id) {
		return menuGroupService.getMenuGroupById(id);
	}

	@PutMapping("/{id}")
	public MenuGroup updateMenuGroup(@PathVariable Long id, @Valid @RequestBody MenuGroup menuGroupRequest) {

		return menuGroupRepository.findById(id).map(menuGroup -> {

			menuGroup.setGroupName(menuGroupRequest.getGroupName());
			menuGroup.setDescription(menuGroupRequest.getDescription());
			menuGroup.setLevel(menuGroupRequest.getLevel());
			menuGroup.setIdSubmenu(menuGroupRequest.getIdSubmenu());
			menuGroup.setIdIcon(menuGroupRequest.getIdIcon());
			menuGroup.setIdUserCreated(menuGroupRequest.getIdUserCreated());
			menuGroup.setIdUserModify(menuGroupRequest.getIdUserModify());
			menuGroup.setCreatedIn(menuGroupRequest.getCreatedIn());
			menuGroup.setLastModify(menuGroupRequest.getLastModify());
			menuGroup.setActive(menuGroupRequest.getActive());

			return menuGroupRepository.save(menuGroup);

		}).orElseThrow(() -> new ResourceNotFoundException("Id da entidade MenuGroup " + id + " não foi encontrado"));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		menuGroupService.delete(id);
	}

}
