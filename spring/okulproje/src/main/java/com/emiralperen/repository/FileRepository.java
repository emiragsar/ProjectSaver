package com.emiralperen.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emiralperen.entites.Files;


public interface FileRepository extends JpaRepository<Files, Integer> {

}
