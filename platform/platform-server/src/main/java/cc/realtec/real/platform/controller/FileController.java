package cc.realtec.real.platform.controller;

import cc.realtec.real.platform.service.MinioService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.InputStream;

@RestController
@RequestMapping("/files")
@Slf4j
public class FileController {

    private final MinioService minioService;

    @Autowired
    public FileController(MinioService minioService) {
        this.minioService = minioService;
    }

    @PostMapping("/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file) {
        try {
            String fileUrl = minioService.uploadFile(file.getOriginalFilename(), file.getInputStream(), file.getContentType());
            log.info("File uploaded successfully. URL: {}", fileUrl);
            return ResponseEntity.ok("File uploaded successfully.");
        } catch (Exception e) {
            log.error("Error in uploading file.", e);
            return ResponseEntity.status(500).body("Error in uploading file.");
        }
    }

    @GetMapping("/download/{filename}")
    public ResponseEntity<InputStreamResource> downloadFile(@PathVariable String filename) {
        try {
            InputStream file = minioService.getFile(filename);
            return ResponseEntity.ok()
                    .contentType(MediaType.APPLICATION_OCTET_STREAM)
                    .body(new InputStreamResource(file));
        } catch (Exception e) {
            return ResponseEntity.status(500).body(null);
        }
    }
}