package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.ContentUser;
import io.github.paulamuhlbach.taf.domain.repository.ContentUserRepository;
import io.github.paulamuhlbach.taf.domain.service.ContentUserService;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@CrossOrigin("http://localhost:4200")
@RequestMapping("/api/contentUsers")
public class ContentUserController {

	@Autowired
	private ContentUserService contentUserService;

	@Autowired
	private ContentUserRepository contentUserRepository;

	@GetMapping
	public List<ContentUser> getContentUsers() {
		return contentUserService.getContentUsers();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postContentUser(@RequestBody ContentUser contentUser) {
		contentUserService.add(contentUser);
	}

	@GetMapping("/{id}")
	public ContentUser getById(@PathVariable(required = true) long id) {
		return contentUserService.getContentUserById(id);
	}

	@PutMapping("/{id}")
	public ContentUser updateContentUser(@PathVariable Long id, @Valid @RequestBody ContentUser contentUseroRequest) {
		return contentUserRepository.findById(id).map(contentUser -> {
			contentUser.setIdContentUserRole(contentUseroRequest.getIdContentUserRole());
			contentUser.setIdUserProfile(contentUseroRequest.getIdUserProfile());
			contentUser.setCreatedIn(contentUseroRequest.getCreatedIn());
			contentUser.setLastModify(contentUseroRequest.getLastModify());
			contentUser.setActive(contentUseroRequest.getActive());
			return contentUserRepository.save(contentUser);

		}).orElseThrow(() -> new ResourceNotFoundException("Id da entidade ContentUser " + id + " não foi encontrado"));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		contentUserService.delete(id);
	}

}
