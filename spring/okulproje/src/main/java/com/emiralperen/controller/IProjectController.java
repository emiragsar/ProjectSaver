package com.emiralperen.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.emiralperen.entites.Project;


public interface IProjectController {
	public ResponseEntity<?> createProject(Project project);
	public ResponseEntity<Project> getProjectById(Integer id);

	public ResponseEntity<List<Project>> getAllProjects();
	public void deleteProject(Integer id);


}
