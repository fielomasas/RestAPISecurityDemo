package com.spring.spring_rest.controller;

import java.util.Collections;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.spring.spring_rest.dto.AuthLoginDto;
import com.spring.spring_rest.dto.AuthRoleRegisterDto;
import com.spring.spring_rest.dto.AuthUserRegisterDto;
import com.spring.spring_rest.entity.Auth_Role;
import com.spring.spring_rest.entity.Auth_User;
import com.spring.spring_rest.repository.AuthRoleRepository;
import com.spring.spring_rest.repository.AuthUserRepository;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;


/**
 * REST endpoints for registering roles and users and authenticating users.
 */
@RestController 
@RequestMapping ("/api/auth")
public class AuthenticationController {

    /** Spring Security component that authenticates submitted credentials. */
    private AuthenticationManager authenticationManager;

    /** Repository used to check and persist user accounts. */
    private AuthUserRepository authUserRepo;

    /** Repository used to retrieve and persist roles. */
    private AuthRoleRepository authRoleRepo;

    /** Encoder used to hash passwords before storing them. */
    private BCryptPasswordEncoder passwordEncoder;

    /**
     * Creates the controller with its authentication, persistence, and
     * password-encoding dependencies.
     *
     * @param authenticationManager component used to authenticate credentials
     * @param authUserRepo repository for user accounts
     * @param authRoleRepo repository for authorization roles
     * @param passwordEncoder encoder used to hash passwords
     */
    public AuthenticationController(
            AuthenticationManager authenticationManager,
            AuthUserRepository authUserRepo,
            AuthRoleRepository authRoleRepo, 
            BCryptPasswordEncoder passwordEncoder) {
        this.authenticationManager = authenticationManager;
        this.authUserRepo = authUserRepo;
        this.authRoleRepo = authRoleRepo;
        this.passwordEncoder = passwordEncoder;
    }

    /**
     * Creates a role unless a role with the same name already exists.
     *
     * @param authRoleRegisterDto request containing the role name
     * @return creation status and a message describing the result
     */
    @PostMapping("/register_authorized_role")
    public ResponseEntity<String> register_authRole(
        @RequestBody AuthRoleRegisterDto authRoleRegisterDto) {
        
        if (authRoleRepo.existsByName(authRoleRegisterDto.getName())) {
            return new ResponseEntity<>(authRoleRegisterDto.getName() + 
                " already created.", HttpStatus.BAD_REQUEST);           
        }

        Auth_Role auth_role = new Auth_Role();
        auth_role.setName(authRoleRegisterDto.getName());

        authRoleRepo.save(auth_role);

        return new ResponseEntity<>(auth_role.getName() + 
            " is successfully created", HttpStatus.OK);
    }

    /**
     * Registers a user with the default {@code USER} role and an encoded password.
     *
     * @param authUserRegisterDto request containing the username and password
     * @return registration status and a message describing the result
     */
    @PostMapping("/register_authorized_user")
    public ResponseEntity<String> register_authUser(
        @RequestBody AuthUserRegisterDto authUserRegisterDto) {
        
        if(authUserRepo.existsByUsername(authUserRegisterDto.getUsername())) {
            return new ResponseEntity<>(authUserRegisterDto.getUsername() + 
                " is already taken.", HttpStatus.BAD_REQUEST);
        }

        Auth_User auth_user = new Auth_User();
        auth_user.setUsername(authUserRegisterDto.getUsername());
        auth_user.setPassword(passwordEncoder.encode(
            authUserRegisterDto.getPassword()
        ));

        Auth_Role auth_role = authRoleRepo.findByName("USER")
            .orElseThrow(() -> new RuntimeException("Role USER not found"));
        auth_user.setRoles(Collections.singletonList(auth_role));

        authUserRepo.save(auth_user);

        return new ResponseEntity<>(auth_user.getUsername() + 
            " is successfully registered", HttpStatus.OK);
    }
    
    /**
     * Authenticates the submitted username and password.
     *
     * @param authLoginDto request containing the credentials
     * @return a success message if authentication succeeds
     */
    @PostMapping("/auth_login")
    public ResponseEntity<String> login(
        @RequestBody AuthLoginDto authLoginDto) {
        
        Authentication authenticate = authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(authLoginDto.getUsername(),
                authLoginDto.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authenticate);

        return new ResponseEntity<>("Sign in successfully", HttpStatus.OK);
    }
    

    /** Returns the authentication manager used by this controller. */
    public AuthenticationManager getAuthenticationManager() {
        return authenticationManager;
    }

    /**
     * Sets the authentication manager used by this controller.
     * @param authenticationManager authentication manager
     */
    public void setAuthenticationManager(
        AuthenticationManager authenticationManager) {
        this.authenticationManager = authenticationManager;
    }

    /** Returns the user repository. */
    public AuthUserRepository getAuthUserRepo() {
        return authUserRepo;
    }

    /**
     * Sets the user repository.
     * @param authUserRepo user repository
     */
    public void setAuthUserRepo(AuthUserRepository authUserRepo) {
        this.authUserRepo = authUserRepo;
    }

    /** Returns the role repository. */
    public AuthRoleRepository getAuthRoleRepo() {
        return authRoleRepo;
    }

    /**
     * Sets the role repository.
     * @param authRoleRepo role repository
     */
    public void setAuthRoleRepo(AuthRoleRepository authRoleRepo) {
        this.authRoleRepo = authRoleRepo;
    }

    /** Returns the password encoder. */
    public BCryptPasswordEncoder getPasswordEncoder() {
        return passwordEncoder;
    }

    /**
     * Sets the password encoder.
     * @param passwordEncoder password encoder
     */
    public void setPasswordEncoder(BCryptPasswordEncoder passwordEncoder) {
        this.passwordEncoder = passwordEncoder;
    }

}
