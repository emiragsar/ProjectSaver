package com.emiralperen.services.impl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.emiralperen.entites.Files; // Files entity'si
import com.emiralperen.entites.Project;
import com.emiralperen.repository.FileRepository;
import com.emiralperen.repository.ProjectRepository;
import com.emiralperen.services.IFileService;

@Service
public class FileServiceImpl implements IFileService {

    @Autowired
    private ProjectRepository projectRepository;

    @Autowired
    private FileRepository fileRepository;

    @Value("${file.upload-dir}")
    private String uploadDir;
    
    @Override
    public String uploadFile(Integer projectId, MultipartFile file) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        try {
           
            String fileExtension = getFileExtension(file.getOriginalFilename());
            String originalFileName = file.getOriginalFilename();
            String uniqueFileName = System.currentTimeMillis() + "_" + originalFileName;

            
            System.out.println(originalFileName);

            
            Path filePath = Paths.get(uploadDir, uniqueFileName);
            File dest = new File(filePath.toString());
            file.transferTo(dest); 

           
            Files newFile = new Files();
            newFile.setFileName(uniqueFileName);
            newFile.setProject(project);
            newFile.setOriginalFileName(originalFileName);
            fileRepository.save(newFile);

            return "File uploaded successfully: " + uniqueFileName;
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("File upload failed: " + e.getMessage());
        }
    }

    @Override
    public List<String> getFiles(Integer projectId) {
        Project project = projectRepository.findById(projectId)
                .orElseThrow(() -> new RuntimeException("Project not found"));

        
        return project.getFiles().stream()
                .map(Files::getFileName)
                .collect(Collectors.toList());
    }

    private String getFileExtension(String fileName) {
        int dotIndex = fileName.lastIndexOf(".");
        if (dotIndex == -1) {
            return ""; 
        }
        return fileName.substring(dotIndex);
    }
}
