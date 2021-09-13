package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.paulamuhlbach.taf.domain.model.PageContentFile;
import io.github.paulamuhlbach.taf.domain.service.PageContentFileService;

import io.github.paulamuhlbach.taf.api.message.*;

@Controller
@CrossOrigin("http://localhost:4200")
public class PageContentFileController {

	@Autowired
	private PageContentFileService pageContentFileService;

	@PostMapping("/api/upload/pageContentFiles/content")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			pageContentFileService.store(file);

			message = "Arquivo carregado com sucesso: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Não foi possível carregar o arquivo: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/api/pageContentFiles/content")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = pageContentFileService.getAllFiles().map(pageContentFile -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/pageContentFiles/content/").path(Long.toString(pageContentFile.getId()))
					.path(pageContentFile.getUrlFile()).toUriString();

			return new ResponseFile(pageContentFile.getId(), pageContentFile.getFilename(), fileDownloadUri,
					pageContentFile.getMimetype(), pageContentFile.getFile().length);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/api/pageContentFiles/content/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		PageContentFile pageContentFile = pageContentFileService.getFile(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION,
						"attachment; filename=\"" + pageContentFile.getFilename() + "\"")
				.body(pageContentFile.getFile());
	}

	@DeleteMapping("/api/pageContentFiles/content/{id}")
	public void delete(@PathVariable(required = true) long id) {
		pageContentFileService.delete(id);
	}

}
