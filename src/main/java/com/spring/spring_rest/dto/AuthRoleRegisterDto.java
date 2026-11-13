package com.spring.spring_rest.dto;

/**
 * Request data for creating an authorization role.
 */
public class AuthRoleRegisterDto {

    /** Name of the role to create. */
    private String name;

    /**
     * Creates role registration data.
     *
     * @param name role name
     */
    public AuthRoleRegisterDto(String name) {
        this.name = name;
    }

    /** Returns the role name. */
    public String getName() {
        return name;
    }

    /**
     * Sets the role name.
     * @param name role name
     */
    public void setName(String name) {
        this.name = name;
    }

}
