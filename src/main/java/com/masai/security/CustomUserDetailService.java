package com.masai.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.blog.entities.User;
import com.blog.exceptions.UserException;
import com.blog.repositories.UserRepo;

@Service
public class CustomUserDetailService implements UserDetailsService{
	
	@Autowired
	private UserRepo userRepo;

	@Override
	public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
		User user = userRepo.findByEmail(email).orElseThrow(()->new UserException("user","id", 0));
		return user;
	}
}
