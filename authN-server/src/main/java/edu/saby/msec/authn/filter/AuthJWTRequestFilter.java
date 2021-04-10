package edu.saby.msec.authn.filter;

import edu.saby.msec.authz.filter.JWTRequestFilter;
import org.springframework.stereotype.Component;

import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author Abhishek Sarkar
 *
 */
@Component
public class AuthJWTRequestFilter extends JWTRequestFilter {

    @Override
    protected String doRefreshTokenHook(HttpServletResponse response, String token) {
        //No-Op
        return token;
    }
}
