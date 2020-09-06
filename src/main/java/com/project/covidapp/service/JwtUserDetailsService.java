package com.project.covidapp.service;

import java.util.ArrayList;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
public class JwtUserDetailsService implements UserDetailsService{

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		//@Value("${spring.security.user.name}")
		//private String username2;
		if("user".equals(username)) {
			return new User("user", 
					"$2a$10$ovlBprRYo15AOhWgKpBqE.7xOmkFXN7uxaODgc71CC3prEQYZdKeG",
					new ArrayList<>());
		}
		else {
			throw new UsernameNotFoundException("User Not Found with username:" + username);
		}
	}
	

}
