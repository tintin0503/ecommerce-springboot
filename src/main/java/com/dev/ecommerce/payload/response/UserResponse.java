package com.dev.ecommerce.payload.response;

import com.dev.ecommerce.payload.UserDto;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

@EqualsAndHashCode(callSuper = true)
@Data
@NoArgsConstructor
@AllArgsConstructor
public class UserResponse extends PageResponse {

    private List<UserDto> content;
}
