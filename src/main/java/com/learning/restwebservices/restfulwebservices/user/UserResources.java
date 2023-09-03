package com.learning.restwebservices.restfulwebservices.user;

import java.net.URI;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResources {

	private UserDaoService service;
	
	public UserResources(UserDaoService service) {
		
	this.service =service;	
	}
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		
		return service.findAll();
	}
	
	
	@GetMapping("/users/{id}")
	public User retriveAllUsers(@PathVariable int id){
		
		return service.findOne(id);
	}
	
	@PostMapping("/users")
	public void createUser(@RequestBody User user) {
		
		service.save(user);
		
		User savedUser=service.save(user);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path(null).buildAndExpand(savedUser.getId()).toUri();
		ResponseEntity.created(location).build();
	}
}
