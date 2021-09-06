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

import io.github.paulamuhlbach.taf.domain.model.Imagem;
import io.github.paulamuhlbach.taf.domain.service.ImagemService;
import io.github.paulamuhlbach.taf.api.message.*;

@Controller
@CrossOrigin("http://localhost:4200")
public class ImagemController {

	@Autowired
	private ImagemService imagemService;

	@PostMapping("/upload/userProfile/foto")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			imagemService.store(file);

			message = "Arquivo carregado com sucesso: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Não foi possível carregar o arquivo: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/userProfile/foto")
	/* @GetMapping("/AlocadoFotos") */
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = imagemService.getAllFiles().map(imagem -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath().path("/userProfile/foto/")
					.path(Long.toString(imagem.getId())).path(imagem.getUrlFile()).toUriString();

			return new ResponseFile(imagem.getId(), imagem.getFilename(), fileDownloadUri, imagem.getMimetype(),
					imagem.getFile().length);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/userProfile/foto/{id}")
	/* @GetMapping("/AlocadoFotos/{id}") */
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		Imagem imagem = imagemService.getFile(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imagem.getFilename() + "\"")
				.body(imagem.getFile());
	}

}
