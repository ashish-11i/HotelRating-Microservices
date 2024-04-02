package com.cwa.user.service.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.cwa.user.service.entities.User;
import com.cwa.user.service.services.UserService;

@RestController
@RequestMapping("/users")
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 = userService.saveUser(user);
//		return new ResponseEntity<User>(user1, HttpStatus.OK);
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getSingleUser(@PathVariable("userId") String userId){
		User user1 = userService.getUser(userId);
		return ResponseEntity.ok(user1);
	}
	
	@GetMapping
	public ResponseEntity<List<User>> getAllusers(){
		List<User> user1 = userService.getAllUsers();
		return ResponseEntity.ok(user1);
	}
}
