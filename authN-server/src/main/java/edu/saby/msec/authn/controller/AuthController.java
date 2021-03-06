package edu.saby.msec.authn.controller;

import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
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
import edu.saby.msec.authn.model.AuthenticationResponse;
import edu.saby.msec.authz.Constants;
import edu.saby.msec.authz.util.JWTUtils;

import java.util.Optional;

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
	public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) {
		AuthenticationResponse response = new AuthenticationResponse();
		
		Optional<Authentication> authentication = Optional.ofNullable(SecurityContextHolder.getContext().getAuthentication());
		if(!StringUtils.equals(authentication.map(Authentication::getName).orElse(Constants.ANONYMOUS_USER), Constants.ANONYMOUS_USER))
			return prepareResponseEntity(response, (String) authentication.get().getCredentials());
		
		try {
			authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
					authenticationRequest.getUsername(), authenticationRequest.getPassword()));
		} catch(BadCredentialsException e) {
			//TODO logging
			response.setErrorMessage(e.getMessage());
			return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(response);
		}

		final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getUsername());

		final String token = jwtTokenUtil.generateToken(userDetails, authenticationRequest.isStayLoggedIn());

		return prepareResponseEntity(response, token);
	}

	private ResponseEntity<AuthenticationResponse> prepareResponseEntity(AuthenticationResponse response, String token) {
		response.setData("SUCCESS");
		return ResponseEntity.ok().header(HttpHeaders.AUTHORIZATION, Constants.AUTH_TOKEN_PREFIX + Constants.AUTH_TOKEN_SEPARATOR + token).body(response);
	}

}
