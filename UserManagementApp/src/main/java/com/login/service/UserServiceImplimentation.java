package com.login.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;
import com.login.model.UserDetails;
import com.login.repository.UserRepository;

import jakarta.servlet.http.HttpSession;

@Service
public class UserServiceImplimentation implements UserService{
	
	
	@Autowired
	public UserRepository userRepository; 
	@Autowired
	private BCryptPasswordEncoder passwordEncode;
	@Override
	public UserDetails createUser(UserDetails user) {
		user.setPassword(passwordEncode.encode(user.getPassword()));
		user.setRole("ROLE_USER");
		return userRepository.save(user);
	}

	@Override
	public boolean checkEmail(String email) {
		
		return userRepository.existsByEmail(email);
	}

	
	public void function( UserDetails user,HttpSession session) {
		String s = ""; 			
		boolean condition = user.getEmail() == s ;

		boolean f=checkEmail(user.getEmail());
		if(f) {
			//System.out.println("User with same email already exists");
			//value is set, to show it we have to fetch it from register
			session.setAttribute("message", "User with same email already exists");
		}
		else if(condition) {
			//System.out.println("User with same email already exists");
			//value is set, to show it we have to fetch it from register
			session.setAttribute("message", "Enter the details");
		}
		else {
			UserDetails userDetails= createUser(user);
			if(userDetails!=null)
				//System.out.println("Register Sucessfully");

				session.setAttribute("message", "Register sucessfully");
			else {
				//System.out.println("Some error in server");
				session.setAttribute("message", "Some error in server");
			}
		}

	}

	
	
	
	
}