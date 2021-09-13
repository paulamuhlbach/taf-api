package io.github.paulamuhlbach.taf.api.controller;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import io.github.paulamuhlbach.taf.domain.model.Imagem;
import io.github.paulamuhlbach.taf.domain.service.ImagemStorageService;
import io.github.paulamuhlbach.taf.api.message.ResponseFile;
import io.github.paulamuhlbach.taf.api.message.ResponseMessage;

@Controller
@CrossOrigin("http://localhost:4200")
public class ImagemController {

	@Autowired
	private ImagemStorageService imagemService;

	@PostMapping("/api/userProfile/imagem/upload")
	public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
		String message = "";
		try {
			imagemService.store(file);

			message = "Uploaded the file successfully: " + file.getOriginalFilename();
			return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
		} catch (Exception e) {
			message = "Could not upload the file: " + file.getOriginalFilename() + "!";
			return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
		}
	}

	@GetMapping("/api/userProfile/imagem/files")
	public ResponseEntity<List<ResponseFile>> getListFiles() {
		List<ResponseFile> files = imagemService.getAllFiles().map(imagem -> {
			String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
					.path("/api/userProfile/imagem/files/").path(Long.toString(imagem.getId())).toUriString();

			return new ResponseFile(imagem.getName(), fileDownloadUri, imagem.getType(), imagem.getData().length);
		}).collect(Collectors.toList());

		return ResponseEntity.status(HttpStatus.OK).body(files);
	}

	@GetMapping("/api/userProfile/imagem/files/{id}")
	public ResponseEntity<byte[]> getFile(@PathVariable Long id) {
		Imagem imagem = imagemService.getFile(id);

		return ResponseEntity.ok()
				.header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + imagem.getName() + "\"")
				.body(imagem.getData());
	}

}
