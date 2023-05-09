package com.example.demo;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/users")
public class UserDetailsController {

	@Autowired
	private UserService service;

	@GetMapping("/")
	public List<UserDetails> getAllUsers() {

		return this.service.getAllUsers();
	}

//	@PreAuthorize("hasRole('ADMIN')")
	@GetMapping("/{name}")
	public UserDetails getAllUser(@PathVariable("name") String name) {

		return this.service.getUsers(name);
	}

	@PostMapping("/")
	public UserDetails add(@RequestBody UserDetails userDetails) {

		return this.service.addUser(userDetails);
	}

}
