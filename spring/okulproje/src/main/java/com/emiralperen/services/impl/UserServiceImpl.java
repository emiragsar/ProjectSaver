package com.emiralperen.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.emiralperen.dto.DtoUser;
import com.emiralperen.dto.DtoUserIU;
import com.emiralperen.entites.User;
import com.emiralperen.repository.UserRepository;
import com.emiralperen.services.IUserService;

@Service

public class UserServiceImpl implements IUserService {
	
	@Autowired
	private UserRepository userRepository;
	
	
	@Override
	public Optional<User> validateUser(String username, String password) {
        Optional<User> user = userRepository.findByUsername(username);
        if (user.isPresent() && user.get().getPassword().equals(password)) {
            return user;
        }
        return Optional.empty();
    }
	
	@Override
	public DtoUser saveUser(DtoUserIU dtoUserIU) {
		User user = new User();
		DtoUser response = new DtoUser();
		BeanUtils.copyProperties(dtoUserIU, user);
		User dbUser = userRepository.save(user);
		BeanUtils.copyProperties(dbUser, response);
		return response;
	}


	@Override
	public List<DtoUser> getAllUsers() {
		List<DtoUser> dtoList = new ArrayList<>();
		List<User> userList = userRepository.findAll();
		for (User user : userList) {
			DtoUser dtoUser = new DtoUser();
			BeanUtils.copyProperties(user, dtoUser);
			dtoList.add(dtoUser);
		}
		return dtoList;
		
	}
	


	@Override
	public DtoUser getUserById(Integer id) {
		DtoUser dtoUser = new DtoUser();
		Optional<User> optional = userRepository.findById(id);
		if(optional.isPresent()) {
			User dbUser = optional.get();
			BeanUtils.copyProperties(dbUser, dtoUser);
		}
		return dtoUser;
	}
	
}
