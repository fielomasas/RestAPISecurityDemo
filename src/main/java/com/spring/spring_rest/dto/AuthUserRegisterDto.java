package com.spring.spring_rest.dto;

/**
 * Request data for registering a user account.
 */
public class AuthUserRegisterDto {

    /** Login name requested for the new account. */
    private String username;

    /** Plain-text password supplied for encoding before storage. */
    private String password;

    /**
     * Creates registration data.
     *
     * @param username requested login name
     * @param password plain-text password from the request
     */
    public AuthUserRegisterDto(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /** Returns the requested login name. */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the requested login name.
     * @param username requested login name
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Returns the supplied password. */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the supplied password.
     * @param password plain-text password from the request
     */
    public void setPassword(String password) {
        this.password = password;
    }

    

}
