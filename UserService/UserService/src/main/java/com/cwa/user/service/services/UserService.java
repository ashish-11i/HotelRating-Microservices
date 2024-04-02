package com.cwa.user.service.services;

import java.util.List;

import com.cwa.user.service.entities.User;

public interface UserService {

	User saveUser(User user);
	
	List<User> getAllUsers();
	
	User getUser(String userId);
	
	//TODO: DELETE
	//TODO: UPDATE
}
