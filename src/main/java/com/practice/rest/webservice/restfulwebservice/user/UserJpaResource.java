package com.practice.rest.webservice.restfulwebservice.user;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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
public class UserJpaResource {
	
	@Autowired
	private UserRepository userRepository;
	
	@Autowired
	private PostRepository postRepository;
	
	@GetMapping("/jpa/users")
	public List<User> retriveAllUsers(){
		return userRepository.findAll();
	}
	
	@GetMapping("/jpa/users/{id}")
	public User  getSpecificUser(@PathVariable int id){
		Optional<User> searchedUser= userRepository.findById(id);
		if(!searchedUser.isPresent())
			 throw new UserNotFoundException("Id-"+id+" not found");
		
		// HEATOAS implementation
		/*Resource<User> resource = new Resource<User>(searchedUser);
		ControllerLinkBuilder linkTo = 
				linkTo(methodOn(this.getClass()).retrieveAllUsers());
		
		resource.add(linkTo.withRel("all-users"));*/
		
		return searchedUser.get();
	}
	
	@PostMapping("/jpa/users")
	public ResponseEntity createUser(@Valid @RequestBody User user){
		User savedUser=userRepository.save(user);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
					 .path("/{id}")
					 .buildAndExpand(savedUser.getId())
					 .toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/users/{id}")
	public void deleteUser(@PathVariable int id){
		userRepository.deleteById(id);
	}
	
	@GetMapping("/jpa/users/{id}/posts")
	public List<Post> retriveAllPostsOfUser(@PathVariable int id){
		Optional<User> searchedUser=userRepository.findById(id);
		if(!searchedUser.isPresent())
			throw new UserNotFoundException("Id-"+id+" not found");
		return searchedUser.get().getPosts();
	}
	
	@PostMapping("/jpa/users/{id}/posts")
	public ResponseEntity createPost(@PathVariable Integer id, @RequestBody Post post){
		Optional<User> searchedUser=userRepository.findById(id);
		if(!searchedUser.isPresent())
			throw new UserNotFoundException("Id-"+id+" not found");
		
		post.setUser(searchedUser.get());
		Post savedPost = postRepository.save(post);
		URI location=ServletUriComponentsBuilder.fromCurrentRequest()
					 .path("/{id}")
					 .buildAndExpand(savedPost.getId())
					 .toUri();
		
		return ResponseEntity.created(location).build();
	}
	
	@DeleteMapping("/jpa/posts/{id}")
	public void deletePost(@PathVariable int id){
		postRepository.deleteById(id);
	}
}
