package com.emiralperen.controller.impl;
import org.springframework.web.bind.annotation.CrossOrigin;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.emiralperen.controller.IUserController;
import com.emiralperen.dto.DtoUser;
import com.emiralperen.dto.DtoUserIU;
import com.emiralperen.entites.User;
import com.emiralperen.services.IUserService;

@RestController
@RequestMapping("/users")
@CrossOrigin
public class UserControllerImpl implements IUserController {
	
	@Autowired
	private IUserService userService;
	
	@Override
	@PostMapping(path = "/save")
	public DtoUser saveUser(@RequestBody DtoUserIU dtoUserIU) {
		return userService.saveUser(dtoUserIU);
	}
	
	@Override
	@PostMapping("/login")
    public ResponseEntity<User> loginUser(@RequestBody User loginData) {
        Optional<User> user = userService.validateUser(loginData.getUsername(), loginData.getPassword());
        return user.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.status(401).build());
    }

 
	@GetMapping(path = "/list")
	@Override
	public List<DtoUser> getAllUsers() {
		
		return userService.getAllUsers();
	}

	@GetMapping(path = "/list/{id}")
	@Override
	public DtoUser getUserById(@PathVariable(name = "id") Integer id) {
		return userService.getUserById(id);
	}
	

}
