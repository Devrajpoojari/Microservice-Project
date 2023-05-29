package com.icwd.user.service.service;

import java.util.List;

import com.icwd.user.service.entities.User;
import com.icwd.user.service.exceptions.ResourceNotFoundException;

public interface UserService {

	User saveUser(User u);

	List<User> getAllUser();
	
	User getUserById(String id) throws ResourceNotFoundException;

}
