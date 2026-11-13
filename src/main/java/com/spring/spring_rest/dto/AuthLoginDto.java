package com.spring.spring_rest.dto;

/**
 * Request data for authenticating a user.
 */
public class AuthLoginDto {

    /** Login name supplied for authentication. */
    private String username;

    /** Plain-text password supplied for authentication. */
    private String password;
    
    /**
     * Creates login request data.
     *
     * @param username login name
     * @param password plain-text password
     */
    public AuthLoginDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /** Returns the submitted login name. */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the submitted login name.
     * @param username login name
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Returns the submitted password. */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the submitted password.
     * @param password plain-text password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    

}
