package com.emiralperen.repository;


import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.emiralperen.entites.User;


public interface UserRepository extends JpaRepository<User, Integer> {
	
    Optional<User> findByUsername(String username);


}
