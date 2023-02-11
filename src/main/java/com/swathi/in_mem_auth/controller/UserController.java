package com.swathi.in_mem_auth.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class UserController {

	@GetMapping("/hello")
	public String sayHello() {
		return "Hello World!";
	}


	@GetMapping("/user") 
	public String sayUser() {
		return "Hello User!"; 
	}

	@GetMapping("/admin") 
	public String sayAdmin() {
		return "Hello Admin!"; 
	}

	@GetMapping("/") 
	public String sayAll() { 
		return "Hello Everyone!"; 
	}

}
