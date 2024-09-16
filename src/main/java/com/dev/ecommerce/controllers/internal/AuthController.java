package com.dev.ecommerce.controllers.internal;

import com.dev.ecommerce.payload.LoginCredentials;
import com.dev.ecommerce.payload.UserDto;
import com.dev.ecommerce.security.JWTUtil;
import com.dev.ecommerce.service.UserService;
import com.dev.ecommerce.util.Constants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import java.util.Collections;
import java.util.Map;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API + Constants.INTERNAL_PATH)
@SecurityRequirement(name = Constants.SECURITY_NAME)
public class AuthController {

    @Autowired
    private UserService userService;

    @Autowired
    private JWTUtil jwtUtil;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @PostMapping("/register")
    public ResponseEntity<Map<String, Object>> register(@Valid @RequestBody UserDto userDto) {

        String encodePass = passwordEncoder.encode(userDto.getPassword());
        userDto.setPassword(encodePass);
        userService.registerUser(userDto);

        String token = jwtUtil.generateToken(userDto.getEmail());
        return new ResponseEntity<>(Collections.singletonMap("token", token), HttpStatus.CREATED);
    }

    @PostMapping("/login")
    public Map<String, Object> login(@Valid @RequestBody LoginCredentials credentials) {

        UsernamePasswordAuthenticationToken authCredentials = new UsernamePasswordAuthenticationToken(
            credentials.getEmail(), credentials.getPassword());

        authenticationManager.authenticate(authCredentials);

        String token = jwtUtil.generateToken(credentials.getEmail());

        return Collections.singletonMap("token", token);
    }
}
