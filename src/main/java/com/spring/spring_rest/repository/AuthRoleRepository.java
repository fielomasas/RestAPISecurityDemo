package com.spring.spring_rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.spring_rest.entity.Auth_Role;

/**
 * Persistence operations for {@link Auth_Role} records.
 */
public interface AuthRoleRepository extends JpaRepository<Auth_Role, Long>{

    /**
     * Finds a role by name.
     *
     * @param theName role name to search for
     * @return the matching role, or empty if no role exists
     */
    Optional<Auth_Role> findByName(String theName);

    /**
     * Checks whether a role name is already present.
     *
     * @param theUsername role name to check
     * @return {@code true} if a matching role exists
     */
    Boolean existsByName(String theUsername);
}
