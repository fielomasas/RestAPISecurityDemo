package com.spring.spring_rest.service;

import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.User;

import com.spring.spring_rest.entity.Auth_Role;
import com.spring.spring_rest.entity.Auth_User;
import com.spring.spring_rest.repository.AuthUserRepository;


/**
 * Loads application users and converts their stored roles for Spring Security.
 */
@Service 
public class CustomUserDetailsService implements UserDetailsService {

    /** Repository used to retrieve user records by login name. */
    private final AuthUserRepository authUserRepo;

    /**
     * Creates the service with its user repository dependency.
     *
     * @param authUserRepo repository used to look up users
     */
    public CustomUserDetailsService(AuthUserRepository authUserRepo) {
        this.authUserRepo = authUserRepo;
    }

    /**
     * Loads a user record and adapts it to 
     * Spring Security's user representation.
     *
     * @param theUsername login name to look up
     * @return Spring Security user details, 
     * including encoded password and roles
     * @throws UsernameNotFoundException if no user 
     * has the supplied login name
     */
    public UserDetails loadUserByUsername(String theUsername) {
        Auth_User user = authUserRepo.findByUsername(theUsername)
            .orElseThrow(
                () -> new UsernameNotFoundException("Username " +
                        theUsername + " does not exist")
            );
        
            return new User(user.getUsername(),
                            user.getPassword(),
                            mapRolesToAuthorities(user.getRoles()));
    }

    /**
     * Converts persisted roles into Spring Security authorities.
     *
     * @param roles roles assigned to the user
     * @return corresponding granted authorities
     */
    private Collection<GrantedAuthority> mapRolesToAuthorities(
        List<Auth_Role> roles) {
        return roles.stream().map(role -> new SimpleGrantedAuthority(
            role.getName())).collect(Collectors.toList());
    }
}
