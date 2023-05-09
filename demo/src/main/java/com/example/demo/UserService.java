package com.example.demo;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	List<UserDetails> user = new ArrayList<>();

	public UserService() {
		user.add(new UserDetails("xyz", "xyz"));
		user.add(new UserDetails("xyz1", "xyz1"));
	}

	public List<UserDetails> getAllUsers() {
		return this.user;
	}

	public UserDetails getUsers(String userName) {

		return this.user.stream().filter(user -> user.getName().equals(userName)).findAny().orElse(null);

	}

	public UserDetails addUser(UserDetails details) {
		this.user.add(details);
		return details;

	}
}
