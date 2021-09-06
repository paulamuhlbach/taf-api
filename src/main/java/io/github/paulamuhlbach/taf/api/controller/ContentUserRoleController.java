package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.ContentUserRole;
import io.github.paulamuhlbach.taf.domain.repository.ContentUserRoleRepository;
import io.github.paulamuhlbach.taf.domain.service.ContentUserRoleService;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@RequestMapping("/contentUserRoles")
public class ContentUserRoleController {

	@Autowired
	private ContentUserRoleService contentUserRoleService;

	@Autowired
	private ContentUserRoleRepository contentUserRoleRepository;

	@GetMapping
	public List<ContentUserRole> getContentUserRoles() {
		return contentUserRoleService.getContentUserRoles();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postContentUserRole(@RequestBody ContentUserRole contentUserRole) {
		contentUserRoleService.add(contentUserRole);
	}

	@GetMapping("/{id}")
	public ContentUserRole getById(@PathVariable(required = true) long id) {
		return contentUserRoleService.getContentUserRoleById(id);
	}

	@PutMapping("/{id}")
	public ContentUserRole updateContentUserRole(@PathVariable Long id,
			@Valid @RequestBody ContentUserRole contentUserRoleRequest) {
		return contentUserRoleRepository.findById(id).map(contentUserRole -> {
			contentUserRole.setDescription(contentUserRoleRequest.getDescription());
			contentUserRole.setActive(contentUserRoleRequest.getActive());
			return contentUserRoleRepository.save(contentUserRole);

		}).orElseThrow(
				() -> new ResourceNotFoundException("Id da entidade ContentUserRole " + id + " não foi encontrado"));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		contentUserRoleService.delete(id);
	}

}
