package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.UserProfile;
import io.github.paulamuhlbach.taf.domain.repository.UserProfileRepository;
import io.github.paulamuhlbach.taf.api.exceptionhandler.ResourceNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public class UserProfileService {

    @Autowired
    private UserProfileRepository repository;

    public void add(UserProfile userProfile) {
        repository.save(toEntity(userProfile));
    }

    public void delete(long id) {
        repository.deleteById(id);
    }

    public List<UserProfile> getUserProfiles() {
        return (List<UserProfile>) repository.findAll();
    }

    public UserProfile getUserProfileById(long id) {
        Optional<UserProfile> optionalUserProfile = repository.findById(id);
        return optionalUserProfile.orElseThrow(
                () -> new ResourceNotFoundException("O id da entity userProfile " + id + " não foi encontrado"));
    }

    private UserProfile toEntity(UserProfile userProfile) {

        UserProfile entity = new UserProfile();
        entity.setPrimeiroNome(userProfile.getPrimeiroNome());
        entity.setSobrenome(userProfile.getSobrenome());
        entity.setUserLogin(userProfile.getUserLogin());
        entity.setPtafSenha(userProfile.getPtafSenha());
        entity.setIdUserImage(userProfile.getIdUserImage());
        entity.setIdEndereco(userProfile.getIdEndereco());
        entity.setTelefone(userProfile.getTelefone());
        entity.setIdInstituicao(userProfile.getIdInstituicao());
        entity.setCpf(userProfile.getCpf());
        entity.setEmail(userProfile.getEmail());

        return entity;
    }
}
