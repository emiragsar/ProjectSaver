package com.emiralperen.controller.impl;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emiralperen.controller.IProjectController;
import com.emiralperen.entites.Project;
import com.emiralperen.services.IProjectService;


@RestController
@RequestMapping("/project")
@CrossOrigin(origins = "http://localhost:5173")
public class ProjectControllerImpl implements IProjectController {

		
		
		@Autowired
		private IProjectService projectService;
		
		
		@PostMapping("/create")
		@Override
		public ResponseEntity<Project> createProject(@RequestBody Project project) {
		    if (project.getUser() == null) {
		        throw new IllegalArgumentException("User cannot be null.");
		    }
		    Project savedProject = projectService.createProject(project);
		    return ResponseEntity.ok(savedProject);
		}
		
		
		@GetMapping("/list")
		@Override
		public ResponseEntity<List<Project>> getAllProjects() {
		    List<Project> projects = projectService.getAllProjects();
		    return ResponseEntity.ok(projects);
		}
		
		@Override
		@GetMapping("/{id}")
		 public ResponseEntity<Project> getProjectById(@PathVariable(name = "id") Integer id) {
	        Project project = projectService.getProjectById(id);
	        if (project != null) {
	            return ResponseEntity.ok(project);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null); 
	        }
	    }
		
		@Override
		@DeleteMapping(path = "/delete/{id}")
		public void deleteProject(@PathVariable(name ="id") Integer id) {
			projectService.deleteProject(id);
		}
		
		

}
		
		
		
