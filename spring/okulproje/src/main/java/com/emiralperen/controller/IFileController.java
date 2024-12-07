package com.emiralperen.controller;

import java.util.List;

import org.springframework.core.io.FileSystemResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.multipart.MultipartFile;

public interface IFileController {
	public ResponseEntity<String> uploadFile(Integer projectId,MultipartFile file);
    public ResponseEntity<List<String>> getFiles( Integer projectId);
    public ResponseEntity<FileSystemResource> getFile(String fileName);

}
