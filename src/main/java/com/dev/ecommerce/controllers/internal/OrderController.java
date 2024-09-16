package com.dev.ecommerce.controllers.internal;

import com.dev.ecommerce.payload.OrderDto;
import com.dev.ecommerce.service.OrderService;
import com.dev.ecommerce.util.Constants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API + Constants.INTERNAL_PATH)
@SecurityRequirement(name = "E-Commerce Application")
public class OrderController {

    @Autowired
    public OrderService orderService;

    @PostMapping("/public/users/{emailId}/carts/{cartId}/payments/{paymentMethod}/order")
    public ResponseEntity<OrderDto> orderProducts(@PathVariable String emailId, @PathVariable Long cartId, @PathVariable String paymentMethod) {
        OrderDto order = orderService.placeOrder(emailId, cartId, paymentMethod);

        return new ResponseEntity<>(order, HttpStatus.CREATED);
    }

    @GetMapping("public/users/{emailId}/orders")
    public ResponseEntity<List<OrderDto>> getOrdersByUser(@PathVariable String emailId) {
        List<OrderDto> orders = orderService.getOrdersByUser(emailId);

        return new ResponseEntity<>(orders, HttpStatus.FOUND);
    }

    @GetMapping("public/users/{emailId}/orders/{orderId}")
    public ResponseEntity<OrderDto> getOrderByUser(@PathVariable String emailId, @PathVariable Long orderId) {
        OrderDto order = orderService.getOrder(emailId, orderId);

        return new ResponseEntity<OrderDto>(order, HttpStatus.FOUND);
    }
}
