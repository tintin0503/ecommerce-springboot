package com.dev.ecommerce.controllers.admin;

import com.dev.ecommerce.payload.OrderDto;
import com.dev.ecommerce.payload.response.OrderResponse;
import com.dev.ecommerce.service.OrderService;
import com.dev.ecommerce.util.Constants;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(Constants.API + Constants.ADMIN_PATH)
@SecurityRequirement(name = "E-Commerce Application")
public class OrderAdminController {

    @Autowired
    public OrderService orderService;

    @GetMapping("/orders")
    public ResponseEntity<OrderResponse> getAllOrders(
        @RequestParam(name = "pageNumber", defaultValue = Constants.Pages.PAGE_NUMBER, required = false) Integer pageNumber,
        @RequestParam(name = "pageSize", defaultValue = Constants.Pages.PAGE_SIZE, required = false) Integer pageSize,
        @RequestParam(name = "sortBy", defaultValue = Constants.SORT_ORDERS_BY, required = false) String sortBy,
        @RequestParam(name = "sortOrder", defaultValue = Constants.SORT_DIR, required = false) String sortOrder) {

        OrderResponse orderResponse = orderService.getAllOrders(pageNumber, pageSize, sortBy, sortOrder);

        return new ResponseEntity<>(orderResponse, HttpStatus.FOUND);
    }

    @PutMapping("/user/{emailId}/orders/{orderId}/orderStatus/{orderStatus}")
    public ResponseEntity<OrderDto> updateOrderByUser(@PathVariable String emailId, @PathVariable Long orderId,
        @PathVariable String orderStatus) {

        OrderDto order = orderService.updateOrder(emailId, orderId, orderStatus);

        return new ResponseEntity<>(order, HttpStatus.OK);
    }
}
