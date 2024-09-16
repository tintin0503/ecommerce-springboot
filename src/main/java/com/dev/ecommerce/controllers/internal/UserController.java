package com.dev.ecommerce.controllers.internal;

import com.dev.ecommerce.payload.UserDto;
import com.dev.ecommerce.service.UserService;
import com.dev.ecommerce.util.Constants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API + Constants.INTERNAL_PATH)
@SecurityRequirement(name = "E-Commerce Application")
public class UserController {

    @Autowired
    private UserService userService;

    @GetMapping("/public/users/{userId}")
    public ResponseEntity<UserDto> getUser(@PathVariable Long userId) {
        UserDto user = userService.getUserById(userId);

        return new ResponseEntity<>(user, HttpStatus.FOUND);
    }

    @PutMapping("/public/users/{userId}")
    public ResponseEntity<UserDto> updateUser(@RequestBody UserDto userDTO, @PathVariable Long userId) {
        UserDto updatedUser = userService.updateUser(userId, userDTO);

        return new ResponseEntity<>(updatedUser, HttpStatus.OK);
    }
}
