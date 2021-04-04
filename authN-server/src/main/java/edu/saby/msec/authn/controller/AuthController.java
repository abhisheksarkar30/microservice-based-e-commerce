package edu.saby.msec.authn.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import edu.saby.msec.authn.model.AuthenticationRequest;
import edu.saby.msec.authz.Constants;
import edu.saby.msec.authz.util.JWTUtils;

/**
 * 
 * @author Abhishek Sarkar
 *
 */
@RestController
public class AuthController {
	
	@Autowired
	private AuthenticationManager authenticationManager;

	@Autowired
	private JWTUtils jwtTokenUtil;

	@Autowired
	private UserDetailsService userDetailsService;

	@GetMapping("/test")
	public String home() {
		return "<h1>Welcome!!!</h1>";
	}
	
	@RequestMapping(value = "/authenticate", method = RequestMethod.POST)
	public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception {
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		if(authentication.isAuthenticated())
			return prepareResponseEntity((String) authentication.getCredentials());
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch(BadCredentialsException e) {
			throw new Exception("Incorrect username or password", e);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails, authenticationRequest.isStayLoggedIn());

		return prepareResponseEntity(token);
	}

	private ResponseEntity<String> prepareResponseEntity(String token) {
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, Constants.AUTH_TOKEN_PREFIX + Constants.AUTH_TOKEN_SEPARATOR + token).body("SUCCESS");
	}

}
