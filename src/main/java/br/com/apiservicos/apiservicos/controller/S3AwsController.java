package br.com.apiservicos.apiservicos.controller;

import br.com.apiservicos.apiservicos.services.S3AwsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("s3")
public class S3AwsController {

    @Autowired
    private S3AwsService s3AwsService;

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestBody String file) throws IOException {
        s3AwsService.uploadFile(file);
        return ResponseEntity.ok().body("Imagem enviada.");
    }
}
