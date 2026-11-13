package com.spring.spring_rest.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.persistence.GenerationType;

/**
 * JPA entity representing an authorization role.
 */
@Entity 
@Table (name = "auth_role")
public class Auth_Role {

    /** Database-generated primary key for this role. */
    @Id 
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private Long id;

    /** Name of the role, such as {@code USER}. */
    private String name;

    /** Returns the database identifier of this role. */
    public Long getId() {
        return id;
    }

    /**
     * Sets the database identifier of this role.
     * @param id role identifier
     */
    public void setId(Long id) {
        this.id = id;
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
