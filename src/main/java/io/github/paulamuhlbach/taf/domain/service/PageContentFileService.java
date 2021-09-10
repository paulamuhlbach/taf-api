package io.github.paulamuhlbach.taf.domain.service;

import io.github.paulamuhlbach.taf.domain.model.PageContentFile;
import io.github.paulamuhlbach.taf.domain.repository.PageContentFileRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.stream.Stream;

@Component
public class PageContentFileService {

    @Autowired
    private PageContentFileRepository repository;

    public PageContentFile store(MultipartFile file) throws IOException {
        String fileName = StringUtils.cleanPath(file.getOriginalFilename());

        PageContentFile contentFile = new PageContentFile(fileName, file.getContentType(), file.getBytes());

        return repository.save(contentFile);
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    public PageContentFile getFile(Long id) {
        return repository.findById(id).get();
    }

    public Stream<PageContentFile> getAllFiles() {
        return repository.findAll().stream();
    }

}
