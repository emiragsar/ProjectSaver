package com.emiralperen.services.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emiralperen.entites.Project;
import com.emiralperen.repository.ProjectRepository;
import com.emiralperen.services.IProjectService;

@Service
public class ProjectServiceImpl implements IProjectService {

	

	@Autowired
	private ProjectRepository projectRepository;
	
	
	
	@Override
	public Project createProject(Project project) {
		return projectRepository.save(project);
		
	}
	@Override
	public List<Project> getAllProjects(){
		return projectRepository.findAll();
	}
	
	@Override
    public Project getProjectById(Integer id) {
        return projectRepository.findById(id).orElse(null);
    }
	
	@Override
    public void deleteProject(Integer id) {
        projectRepository.deleteById(id); 
    }

}
