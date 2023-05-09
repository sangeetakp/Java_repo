package com.example.demo;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/public")
public class HomeController {

	@GetMapping("/home")
	public String home() {
		return "home";

	}

	@GetMapping("/login")
	public String login() {
		return "login";

	}

	@GetMapping("/register")
	public String register() {
		return "register";

	}
}