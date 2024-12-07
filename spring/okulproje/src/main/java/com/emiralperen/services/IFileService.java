package com.emiralperen.services;

import java.util.List;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
public interface IFileService {
	public String uploadFile(Integer projectId, MultipartFile file);

    public List<String> getFiles(Integer projectId);

}
