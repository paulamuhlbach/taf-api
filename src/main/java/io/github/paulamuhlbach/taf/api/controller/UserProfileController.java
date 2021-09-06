package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import io.github.paulamuhlbach.taf.domain.model.UserProfile;
import io.github.paulamuhlbach.taf.domain.repository.UserProfileRepository;
import io.github.paulamuhlbach.taf.domain.service.UserProfileService;

import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

@RestController
@RequestMapping("/userProfiles")
public class UserProfileController {

	@Autowired
	private UserProfileService userProfileService;

	@Autowired
	private UserProfileRepository userProfileRepository;

	@GetMapping
	public List<UserProfile> getUserProfiles() {
		return userProfileService.getUserProfiles();
	}

	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public void postUserProfile(@RequestBody UserProfile userProfile) {
		userProfileService.add(userProfile);
	}

	@GetMapping("/{id}")
	public UserProfile getById(@PathVariable(required = true) long id) {
		return userProfileService.getUserProfileById(id);
	}

	@PutMapping("/{id}")
	public UserProfile updateUserProfile(@PathVariable Long id, @Valid @RequestBody UserProfile userProfileRequest) {

		return userProfileRepository.findById(id).map(userProfile -> {

			userProfile.setPrimeiroNome(userProfileRequest.getPrimeiroNome());
			userProfile.setSobrenome(userProfileRequest.getSobrenome());
			userProfile.setUserLogin(userProfileRequest.getUserLogin());
			userProfile.setPtafSenha(userProfileRequest.getPtafSenha());
			userProfile.setIdUserImage(userProfileRequest.getIdUserImage());
			userProfile.setIdEndereco(userProfileRequest.getIdEndereco());
			userProfile.setTelefone(userProfileRequest.getTelefone());
			userProfile.setIdInstituicao(userProfileRequest.getIdInstituicao());
			userProfile.setCpf(userProfileRequest.getCpf());
			userProfile.setEmail(userProfileRequest.getEmail());

			return userProfileRepository.save(userProfile);

		}).orElseThrow(() -> new ResourceNotFoundException("Id da entidade UserProfile " + id + " não foi encontrado"));
	}

	@DeleteMapping("/{id}")
	public void delete(@PathVariable(required = true) long id) {
		userProfileService.delete(id);
	}

}
