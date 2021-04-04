package edu.saby.msec.authz.util;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import edu.saby.msec.authz.ApplicationEnvironmentKeys;
import edu.saby.msec.authz.Constants;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

/**
 *
 * @author Abhishek Sarkar
 *
 */
@Service
public class JWTUtils {

	@Value(ApplicationEnvironmentKeys.JWT_SECRET_KEY)
	private String SECRET_KEY;

	public String extractUsername(String token) {
		return extractClaim(token, Claims::getSubject);
	}
	
	public String extractUsername(Claims allClaims) {
		return allClaims.getSubject();
	}

	public Boolean extractStayLoggedIn(String token) {
		return extractClaim(token, Constants.STAY_LOGGED_IN, Boolean.class);
	}
	
	public Boolean extractStayLoggedIn(Claims allClaims) {
		return extractClaim(allClaims, Constants.STAY_LOGGED_IN, Boolean.class);
	}

	public Date extractExpiration(String token) {
		return extractClaim(token, Claims::getExpiration);
	}

	public Date extractExpiration(Claims allClaims) {
		return allClaims.getExpiration();
	}
	
	private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
		final Claims claims = extractAllClaims(token);
		return claimsResolver.apply(claims);
	}
	
	private <T> T extractClaim(String token, String key, Class<T> requiredClassType) {
		return extractAllClaims(token).get(key, requiredClassType);
	}
	
	private <T> T extractClaim(Claims allClaims, String key, Class<T> requiredClassType) {
		return allClaims.get(key, requiredClassType);
	}

	public Claims extractAllClaims(String token) {
		try {
			return Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
		} catch(ExpiredJwtException e) {
			return e.getClaims();
		}
	}

	public Boolean isTokenExpired(String token) {
		return extractExpiration(token).before(new Date());
	}
	
	public Boolean isTokenExpired(Claims allClaims) {
		return extractExpiration(allClaims).before(new Date());
	}

	public String generateToken(UserDetails userDetails, boolean stayLoggedIn) {
		Map<String, Object> claims = new HashMap<>();
		claims.put(Constants.STAY_LOGGED_IN, stayLoggedIn);
		return createToken(claims, userDetails.getUsername());
	}

	private String createToken(Map<String, Object> claims, String subject) {
		return Jwts.builder().setClaims(claims).setSubject(subject).setIssuedAt(new Date(System.currentTimeMillis()))
				.setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 10))
				.signWith(SignatureAlgorithm.HS256, SECRET_KEY).compact();
	}

	public Boolean validateToken(String token, UserDetails userDetails) {
		final String username = extractUsername(token);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(token));
	}
	
	public Boolean validateToken(Claims allClaims, UserDetails userDetails) {
		final String username = extractUsername(allClaims);
		return (username.equals(userDetails.getUsername()) && !isTokenExpired(allClaims));
	}

}
