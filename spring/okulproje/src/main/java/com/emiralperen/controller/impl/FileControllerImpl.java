package com.emiralperen.controller.impl;

import com.emiralperen.controller.IFileController;
import com.emiralperen.services.IFileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/file")
@CrossOrigin(origins = "http://localhost:5173")
public class FileControllerImpl implements IFileController {

    @Autowired
    private IFileService fileService;
    
    @Value("${file.upload-dir}")
    private String uploadDir;

    
    @Override
    @PostMapping("/{projectId}/upload")
    public ResponseEntity<String> uploadFile(@PathVariable Integer projectId,
                                             @RequestParam("file") MultipartFile file) {
        try {
            String message = fileService.uploadFile(projectId, file);
            return ResponseEntity.ok(message);
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(500).body("File upload failed: " + e.getMessage());
        }
    }

    @Override
    @GetMapping("/{projectId}")
    public ResponseEntity<List<String>> getFiles(@PathVariable Integer projectId) {
        try {
            List<String> files = fileService.getFiles(projectId);
            if (files != null && !files.isEmpty()) {
                return ResponseEntity.ok(files);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }

    @Override
    @GetMapping("/uploads/{fileName}")
    public ResponseEntity<FileSystemResource> getFile(@PathVariable String fileName) {
        try {
            Path filePath = Paths.get(uploadDir, fileName);

            if (Files.exists(filePath)) {
                File file = filePath.toFile();
                FileSystemResource resource = new FileSystemResource(file);

                HttpHeaders headers = new HttpHeaders();
                headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=" + fileName);
                headers.add(HttpHeaders.CONTENT_TYPE, Files.probeContentType(filePath));

                return ResponseEntity.ok()
                        .headers(headers)
                        .body(resource);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
            }
        } catch (Exception e) {
            e.printStackTrace();
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(null);
        }
    }
}
