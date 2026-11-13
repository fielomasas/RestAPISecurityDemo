package com.spring.spring_rest.entity;

import java.util.ArrayList;
import java.util.List;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.Table;
import jakarta.persistence.FetchType;
import jakarta.persistence.CascadeType;


/**
 * JPA entity representing an application user and its assigned roles.
 */
@Entity 
@Table (name = "auth_user")
public class Auth_User {

    /** Database-generated primary key for this user. */
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    /** Unique login name for the user. */
    private String username;

    /** Encoded password used by the authentication provider. */
    private String password;

    /** Roles assigned to this user; loaded eagerly for authentication. */
    @ManyToMany (fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinTable (name = "authusers_authroles",
                joinColumns = @JoinColumn (name = "authuser_id", 
                                            referencedColumnName = "id"),
                inverseJoinColumns = @JoinColumn (name = "authrole_id", 
                                            referencedColumnName = "id"))
    private List<Auth_Role> roles = new ArrayList<>();

    /** Returns the database identifier of this user. */
    public Long getId() {
        return id;
    }

    /**
     * Sets the database identifier of this user.
     * @param id user identifier
     */
    public void setId(Long id) {
        this.id = id;
    }

    /** Returns the user's login name. */
    public String getUsername() {
        return username;
    }

    /**
     * Sets the user's login name.
     * @param username login name
     */
    public void setUsername(String username) {
        this.username = username;
    }

    /** Returns the stored encoded password. */
    public String getPassword() {
        return password;
    }

    /**
     * Sets the stored encoded password.
     * @param password encoded password
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /** Returns the roles assigned to this user. */
    public List<Auth_Role> getRoles() {
        return roles;
    }

    /**
     * Replaces the roles assigned to this user.
     * @param roles roles to assign
     */
    public void setRoles(List<Auth_Role> roles) {
        this.roles = roles;
    }

    
}
