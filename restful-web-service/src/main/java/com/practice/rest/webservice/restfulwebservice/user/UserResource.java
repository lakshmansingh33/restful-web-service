package com.practice.rest.webservice.restfulwebservice.user;

import java.net.URI;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
public class UserResource {

	@Autowired
	private UserDaoService service;
	
	@GetMapping("/users")
	public List<User> retriveAllUsers(){
		return service.findAll();
	}
	
	@GetMapping("/users/{id}")
	public User  getSpecificUser(@PathVariable int id){
		User searchedUser= service.findOne(id);
		if(searchedUser == null)
			 throw new UserNotFoundException("Id-"+id+" not found");
		
		// HEATOAS implementation
		/*Resource<User> resource = new Resource<User>(searchedUser);
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));*/
		
		return searchedUser;
	}
	
	@PostMapping("/users")
	public ResponseEntity createUser(@Valid @RequestBody User user){
		User savedUser=service.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
					 .path("/{id}")
					 .buildAndExpand(savedUser.getId())
					 .toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/users/{id}")
	public void deleteUser(@PathVariable int id){
		User deletedUser= service.deleteUserById(id);
		if(deletedUser == null)
			 throw new UserNotFoundException("Id-"+id+" not found");
		
	}
}
