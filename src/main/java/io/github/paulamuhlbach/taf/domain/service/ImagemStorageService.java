package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.Imagem;
import io.github.paulamuhlbach.taf.domain.repository.ImagemRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Service
public class ImagemStorageService {

    @Autowired
    private ImagemRepository imageRepository;

    public Imagem store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        Imagem imagem = new Imagem(fileName, file.getContentType(), file.getBytes());

        return imageRepository.save(imagem);
    }

    public void delete(Long id) {
        imageRepository.deleteById(id);
    }

    public Imagem getFile(Long id) {
        return imageRepository.findById(id).get();
    }

    public Stream<Imagem> getAllFiles() {
        return imageRepository.findAll().stream();
    }

}
