package com.acropolis.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("auth/admin")
public class AdminController {

	@GetMapping("/home")
	public String homepage() {
		return "Welcome to Admin page";
	}
}
