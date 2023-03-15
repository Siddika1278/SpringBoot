package com.login.repository;

import org.springframework.data.jpa.repository.JpaRepository;


import com.login.model.UserDetails;



public interface UserRepository extends JpaRepository<UserDetails, Integer>{
	public boolean existsByEmail(String email);
	
	public UserDetails findByEmail(String email);
	

}