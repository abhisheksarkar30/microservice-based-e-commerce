package edu.saby.msec.authz.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.google.common.collect.Lists;

/**
 * 
 * @author Abhishek Sarkar
 *
 */
@Service
public class CustomUserDetailService implements UserDetailsService {
	
	@Autowired
	private Environment env;
	
	private static final String APP_NAME = "msec";

	@Override
	public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
		return new User(env.getProperty("spring.security.user.name", APP_NAME),
				env.getProperty("spring.security.user.password", APP_NAME),
				Lists.newArrayList());
	}

}
