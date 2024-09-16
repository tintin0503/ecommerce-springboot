package com.dev.ecommerce.controllers.admin;

import com.dev.ecommerce.payload.CartDto;
import com.dev.ecommerce.service.CartService;
import com.dev.ecommerce.util.Constants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API + Constants.ADMIN_PATH)
@SecurityRequirement(name = "E-Commerce Application")
public class CartAdminController {

    @Autowired
    private CartService cartService;

    @GetMapping("/carts")
    public ResponseEntity<List<CartDto>> getCarts() {

        List<CartDto> cartDTOs = cartService.getAllCarts();

        return new ResponseEntity<>(cartDTOs, HttpStatus.FOUND);
    }
}
