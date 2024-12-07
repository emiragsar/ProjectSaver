package com.emiralperen.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;

import com.emiralperen.dto.DtoUser;
import com.emiralperen.dto.DtoUserIU;
import com.emiralperen.entites.User;


public interface IUserController {

	public DtoUser saveUser(DtoUserIU dtoUserIU);
	public List<DtoUser> getAllUsers();
	public DtoUser getUserById(Integer id);
	public ResponseEntity<User> loginUser(User loginData);
}
