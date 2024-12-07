package com.emiralperen.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emiralperen.entites.Project;



public interface ProjectRepository extends JpaRepository<Project, Integer>{

	List<Project> findByUserId(Integer userId);
    public Project getProjectById(Integer id);


}
