/**
 * 
 */
package edu.saby.msec;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;

import org.springframework.util.AntPathMatcher;

import edu.saby.msec.authz.filter.JWTRequestFilter;

/**
 * @author Abhishek Sarkar
 *
 */
public class AuthorizationFilter extends JWTRequestFilter {
	
	@Override
	protected boolean shouldNotFilter(HttpServletRequest request) throws ServletException {
		//Exclude /authenticate filtering
		return new AntPathMatcher().match("*/authenticate", request.getRequestURI());
	}

}
