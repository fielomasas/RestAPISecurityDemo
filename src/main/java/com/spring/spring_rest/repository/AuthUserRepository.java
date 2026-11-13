package com.spring.spring_rest.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.spring.spring_rest.entity.Auth_User;

/**
 * Persistence operations for {@link Auth_User} records.
 */
public interface AuthUserRepository extends JpaRepository<Auth_User, Long>{

    /**
     * Finds a user by login name.
     *
     * @param theUsername login name to search for
     * @return the matching user, or empty if no user exists
     */
    Optional<Auth_User> findByUsername(String theUsername);

    /**
     * Checks whether a login name is already assigned.
     *
     * @param theUsername login name to check
     * @return {@code true} if a matching user exists
     */
    Boolean existsByUsername(String theUsername);

}
