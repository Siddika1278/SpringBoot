package com.login.service;

import com.login.model.UserDetails;

import jakarta.servlet.http.HttpSession;

public interface UserService {
	public UserDetails createUser(UserDetails user);
	public boolean checkEmail(String email);
	public void function( UserDetails user,HttpSession session);

}