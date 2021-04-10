package edu.saby.msec.authz.filter;

import java.io.IOException;
import java.util.Date;
import java.util.concurrent.TimeUnit;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingClass;
import org.springframework.security.authentication.RememberMeAuthenticationToken;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.config.web.server.ServerHttpSecurity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.security.web.authentication.session.SessionAuthenticationException;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import edu.saby.msec.authz.Constants;
import edu.saby.msec.authz.util.JWTUtils;
import io.jsonwebtoken.Claims;

/**
 * 
 * @author Abhishek Sarkar
 *
 */
@Component
@ConditionalOnMissingClass("edu.saby.msec.authn.filter.AuthJWTRequestFilter")
public class JWTRequestFilter extends OncePerRequestFilter {

    @Autowired
    private UserDetailsService userDetailsService;

    @Autowired
    private JWTUtils jwtUtil;

    @Override
    final protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain)
            throws ServletException, IOException {

    	final String authorizationHeader = request.getHeader(Constants.AUTH_HEADER);
    	
    	Claims allClaims = null;
    	
    	if(StringUtils.isNotBlank(authorizationHeader)) {

    		final String[] authHeaderComponents = authorizationHeader.split(Constants.AUTH_TOKEN_SEPARATOR);

    		String username = null;
    		String token = null;

    		if (authHeaderComponents != null && StringUtils.equals(authHeaderComponents[0], Constants.AUTH_TOKEN_PREFIX)) {
    			token = authHeaderComponents[1];
    			allClaims = jwtUtil.extractAllClaims(token);
				username = jwtUtil.extractUsername(allClaims);

    			if(jwtUtil.isTokenExpired(allClaims)) {
    				long diffInMillies = Math.abs(new Date().getTime() - jwtUtil.extractExpiration(allClaims).getTime());
    			    long diffInDays = TimeUnit.DAYS.convert(diffInMillies, TimeUnit.MILLISECONDS);
    				
					if(jwtUtil.extractStayLoggedIn(allClaims) && diffInDays < 10) {
						token = doRefreshTokenHook(response, token);
						allClaims = jwtUtil.extractAllClaims(token);
					} else {
						throw new SessionAuthenticationException("Session Timeout");
//						throw new ResponseStatusException(HttpStatus.UNAUTHORIZED, "Session Timeout", null);
					}
				}
    		} else {
    			new SessionAuthenticationException("Not yet Logged in!");
    		}

    		if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {

    			UserDetails userDetails = this.userDetailsService.loadUserByUsername(username);

    			if (jwtUtil.validateToken(allClaims, userDetails)) {
    				UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(
    						userDetails, token, userDetails.getAuthorities());
    				usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
    				SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    			}
    		}
    	}
    	
        chain.doFilter(request, response);
    }

	protected String doRefreshTokenHook(HttpServletResponse response, String token) {
		//Create new JWT via Feign client
		response.setHeader(Constants.AUTH_HEADER, Constants.AUTH_TOKEN_PREFIX + Constants.AUTH_TOKEN_SEPARATOR + token);
		return token;
	}

}