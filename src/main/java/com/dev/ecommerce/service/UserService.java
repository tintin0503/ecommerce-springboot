package com.dev.ecommerce.service;

import com.dev.ecommerce.payload.UserDto;
import com.dev.ecommerce.payload.response.UserResponse;

public interface UserService {

    UserDto registerUser(UserDto userDTO);

    UserResponse getAllUsers(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    UserDto getUserById(Long userId);

    UserDto updateUser(Long userId, UserDto userDTO);

    String deleteUser(Long userId);
}
