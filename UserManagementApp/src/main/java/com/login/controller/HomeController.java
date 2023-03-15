package com.login.controller;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import com.login.model.UserDetails;
import com.login.service.UserService;


import jakarta.servlet.http.HttpSession;

@Controller
public class HomeController {
		
	   @Autowired
		UserService userService;
		

		@GetMapping("/")
		public String index() {
			return "index"; 
		}
		
//		@GetMapping("/signin")
//		public String login() {
//			return "login";
//		}
		
		@GetMapping("/register")
		public String register() {
			return "register";
		}
		
		@PostMapping("/createUser")
		public String createuser(@ModelAttribute UserDetails user, HttpSession session) {
			
		userService.function( user, session);							
			
		return "redirect:/register";
		}
		
}