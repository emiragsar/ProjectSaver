package com.emiralperen.services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.emiralperen.entites.Project;

@Service
public interface IProjectService {
	public Project createProject(Project project);
	public List<Project> getAllProjects();
	public void deleteProject(Integer id);
	public Project getProjectById(Integer id);

	
}
