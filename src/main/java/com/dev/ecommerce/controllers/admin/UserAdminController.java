package com.dev.ecommerce.controllers.admin;

import com.dev.ecommerce.payload.response.UserResponse;
import com.dev.ecommerce.service.UserService;
import com.dev.ecommerce.util.Constants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@Tag(name = "Admin User", description = "User APIs for Admin")
@RestController
@RequestMapping(Constants.API + Constants.ADMIN_PATH + "/users")
@SecurityRequirement(name = "E-Commerce Application")
public class UserAdminController {

    @Autowired
    private UserService userService;

    /**
     * get all user by admin
     * @param pageNumber integer
     * @param pageSize integer
     * @param sortBy string
     * @param sortOrder string
     * @return UserResponse
     */
    @GetMapping()
    public ResponseEntity<UserResponse> getUsers(
        @RequestParam(name = "pageNumber", defaultValue = Constants.Pages.PAGE_NUMBER, required = false) Integer pageNumber,
        @RequestParam(name = "pageSize", defaultValue = Constants.Pages.PAGE_SIZE, required = false) Integer pageSize,
        @RequestParam(name = "sortBy", defaultValue = Constants.SORT_USERS_BY, required = false) String sortBy,
        @RequestParam(name = "sortOrder", defaultValue = Constants.SORT_DIR, required = false) String sortOrder) {

        UserResponse userResponse = userService.getAllUsers(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(userResponse, HttpStatus.FOUND);
    }

    /**
     * delete user by id
     * @param userId Long
     * @return response status
     */
    @DeleteMapping("/{userId}")
    public ResponseEntity<String> deleteUser(@PathVariable Long userId) {
        String status = userService.deleteUser(userId);

        return new ResponseEntity<>(status, HttpStatus.OK);
    }
}
