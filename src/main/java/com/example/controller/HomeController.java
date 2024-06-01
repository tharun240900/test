package com.example.controller;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.example.model.User;
import com.example.repository.UserRepo;

@RestController
@RequestMapping("/user")
public class HomeController {
	
	@Autowired
	private UserRepo userRepo;

	@DeleteMapping("/{id}")
	public ResponseEntity<String> deleteUser(@PathVariable long id) {
		userRepo.deleteById(id);
		return new ResponseEntity<>("User Deleted",HttpStatus.OK);
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<User> update(@PathVariable long id,@RequestBody User u) {
		if(userRepo.existsById(id))u.setId(id);
		return new ResponseEntity<>(userRepo.save(u),HttpStatus.OK);
	}
	
	@PostMapping("/save")
	public ResponseEntity<User> save(@RequestBody User u) {
		return new ResponseEntity<>(userRepo.save(u),HttpStatus.CREATED);
	}
	
	@GetMapping("/all")
	public List<User> getAllUsers() {
		return userRepo.findAll();
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<User> getById(@PathVariable long id){
		return new ResponseEntity<>(userRepo.findById(id).get(),HttpStatus.OK); 
	}
}
