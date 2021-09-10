package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.Imagem;
import io.github.paulamuhlbach.taf.domain.repository.ImagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Component
public class ImagemService {

    @Autowired
    private ImagemRepository repository;

    public Imagem store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Imagem imagem = new Imagem(fileName, file.getContentType(), file.getBytes());

        return repository.save(imagem);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public Imagem getFile(Long id) {
        return repository.findById(id).get();
    }

    public Stream<Imagem> getAllFiles() {
        return repository.findAll().stream();
    }

}
