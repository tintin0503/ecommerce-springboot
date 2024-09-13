package com.dev.ecommerce.config;

import com.dev.ecommerce.repositories.impl.UserDetailsServiceImpl;
import com.dev.ecommerce.security.AuthEntryPointJwt;
import com.dev.ecommerce.security.JwtFilter;
import com.dev.ecommerce.util.Constants;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.method.configuration.EnableMethodSecurity;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
@EnableMethodSecurity
public class SecurityConfig {

    @Autowired
    private JwtFilter jwtFilter;

    @Autowired
    private UserDetailsServiceImpl userDetailsServiceImpl;

    @Autowired
    private AuthEntryPointJwt authEntryPointJwt;


    @Bean
    public DaoAuthenticationProvider daoAuthenticationProvider() {
        DaoAuthenticationProvider provider = new DaoAuthenticationProvider();

        provider.setUserDetailsService(userDetailsServiceImpl);
        provider.setPasswordEncoder(passwordEncoder());

        return provider;
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration configuration) throws Exception {
        return configuration.getAuthenticationManager();
    }

    @Bean
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {

        http
            .csrf(AbstractHttpConfigurer::disable)
            .cors(AbstractHttpConfigurer::disable)
            .authorizeHttpRequests(auth ->
                auth
                    .requestMatchers(HttpMethod.OPTIONS,"/**").permitAll()
                    .requestMatchers(Constants.PUBLIC_URLS).permitAll()
                    .requestMatchers(Constants.USER_URLS).hasAnyAuthority(Constants.Roles.USER, Constants.Roles.ADMIN)
                    .requestMatchers(Constants.ADMIN_URLS).hasAuthority(Constants.Roles.ADMIN)
                    .anyRequest().authenticated()
            )
            .exceptionHandling(exception -> exception.authenticationEntryPoint(authEntryPointJwt))
            .sessionManagement(session -> session.sessionCreationPolicy(SessionCreationPolicy.STATELESS));

        http.authenticationProvider(daoAuthenticationProvider());

        return http.build();

    }
}
