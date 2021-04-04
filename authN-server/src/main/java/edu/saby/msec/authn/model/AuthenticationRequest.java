package edu.saby.msec.authn.model;

import java.io.Serializable;

/**
 * 
 * @author Abhishek Sarkar
 *
 */
@SuppressWarnings("serial")
public class AuthenticationRequest implements Serializable {


    private String username;
    private String password;
    private boolean stayLoggedIn;

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStayLoggedIn() {
		return stayLoggedIn;
	}

	public void setStayLoggedIn(boolean stayLoggedIn) {
		this.stayLoggedIn = stayLoggedIn;
	}

	//need default constructor for JSON Parsing
    public AuthenticationRequest() {}

    public AuthenticationRequest(String username, String password) {
        this.setUsername(username);
        this.setPassword(password);
    }
    
    public AuthenticationRequest(String username, String password, boolean stayLoggedIn) {
        this(username, password);
        this.setStayLoggedIn(stayLoggedIn);
    }
}