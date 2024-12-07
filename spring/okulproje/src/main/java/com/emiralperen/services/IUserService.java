package com.emiralperen.services;



import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.emiralperen.dto.DtoUser;
import com.emiralperen.dto.DtoUserIU;
import com.emiralperen.entites.User;



@Service
public interface IUserService {
	public DtoUser saveUser(DtoUserIU dtoUserIU); 
	public List<DtoUser> getAllUsers();	
	public DtoUser getUserById(Integer id);
	public Optional<User> validateUser(String username, String password);

}
