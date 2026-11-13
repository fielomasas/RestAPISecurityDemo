package com.spring.spring_rest.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

import com.spring.spring_rest.service.CustomUserDetailsService;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;

/**
 * Configures HTTP security and authentication-related beans.
 */
@EnableWebSecurity
@Configuration
public class SecurityConfig {

    /** User lookup service used by authentication configuration. */
    private CustomUserDetailsService userDetailsService;

    /**
     * Creates the security configuration with the application user service.
     *
     * @param userDetailsService service that loads users and their roles
     */
    public SecurityConfig(CustomUserDetailsService userDetailsService) {
        this.setUserDetailsService(userDetailsService);
    }

    /**
     * Creates the filter chain, permitting authentication endpoints and
     * requiring authentication for other requests.
     *
     * @param security HTTP security builder
     * @return configured security filter chain
     * @throws Exception if the security chain cannot be configured
     */
    @Bean
    SecurityFilterChain filterChain(HttpSecurity security) 
            throws Exception {
        return security
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(auth -> auth
                                            .requestMatchers("/api/auth/**")
                                            .permitAll()
                                            .anyRequest()
                                            .authenticated())
                .httpBasic(Customizer.withDefaults())
                .build();
    }

    /** Creates the BCrypt password encoder used for password hashing. */
    @Bean 
    BCryptPasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    /**
     * Obtains the authentication manager configured by Spring Security.
     *
     * @param authentication Spring Security authentication configuration
     * @return configured authentication manager
     * @throws Exception if the authentication manager cannot be created
     */
    @Bean 
    AuthenticationManager authenticationManager
        (AuthenticationConfiguration authentication) throws Exception {
        return authentication.getAuthenticationManager();
    }

    /** Returns the configured database-backed user lookup service. */
    public CustomUserDetailsService getUserDetailsService() {
        return userDetailsService;
    }

    /**
     * Sets the user lookup service used by this configuration.
     * @param userDetailsService service that loads application users
     */
    public void setUserDetailsService(
        CustomUserDetailsService userDetailsService) {
        this.userDetailsService = userDetailsService;
    }
}
