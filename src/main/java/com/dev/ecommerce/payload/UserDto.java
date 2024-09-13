package com.dev.ecommerce.payload;

import com.dev.ecommerce.entity.Role;
import java.util.HashSet;
import java.util.Set;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserDto {

    private Long userId;
    private String firstName;
    private String lastName;
    private String mobileNumber;
    private String email;
    private String password;
    private Set<Role> roles = new HashSet<>();

    private CartDto cartDto;
}
