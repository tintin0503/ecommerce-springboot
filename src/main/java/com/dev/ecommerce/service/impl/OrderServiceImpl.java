package com.dev.ecommerce.service.impl;

import com.dev.ecommerce.payload.OrderDto;
import com.dev.ecommerce.payload.response.OrderResponse;
import com.dev.ecommerce.repositories.CartItemRepository;
import com.dev.ecommerce.repositories.CartRepository;
import com.dev.ecommerce.repositories.OrderItemRepository;
import com.dev.ecommerce.repositories.OrderRepository;
import com.dev.ecommerce.repositories.PaymentRepository;
import com.dev.ecommerce.repositories.UserRepository;
import com.dev.ecommerce.service.CartService;
import com.dev.ecommerce.service.OrderService;
import com.dev.ecommerce.service.UserService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    public UserRepository userRepo;

    @Autowired
    public CartRepository cartRepo;

    @Autowired
    public OrderRepository orderRepo;

    @Autowired
    private PaymentRepository paymentRepo;

    @Autowired
    public OrderItemRepository orderItemRepo;

    @Autowired
    public CartItemRepository cartItemRepo;

    @Autowired
    public UserService userService;

    @Autowired
    public CartService cartService;

    @Autowired
    public ModelMapper modelMapper;

    @Override
    public OrderDto placeOrder(String emailId, Long cartId, String paymentMethod) {
        return null;
    }

    @Override
    public OrderDto getOrder(String emailId, Long orderId) {
        return null;
    }

    @Override
    public List<OrderDto> getOrdersByUser(String emailId) {
        return null;
    }

    @Override
    public OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {
        return null;
    }

    @Override
    public OrderDto updateOrder(String emailId, Long orderId, String orderStatus) {
        return null;
    }
}
