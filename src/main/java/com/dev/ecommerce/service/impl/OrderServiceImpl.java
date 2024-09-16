package com.dev.ecommerce.service.impl;

import com.dev.ecommerce.entity.Cart;
import com.dev.ecommerce.entity.CartItem;
import com.dev.ecommerce.entity.Order;
import com.dev.ecommerce.entity.OrderItem;
import com.dev.ecommerce.entity.Payment;
import com.dev.ecommerce.entity.Product;
import com.dev.ecommerce.exception.ApiException;
import com.dev.ecommerce.exception.ResourceNotFoundException;
import com.dev.ecommerce.payload.OrderDto;
import com.dev.ecommerce.payload.OrderItemDto;
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
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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

        Cart cart = cartRepo.findCartByEmailAndCartId(emailId, cartId);

        if (cart == null) {
            throw new ResourceNotFoundException("Cart", "cartId", cartId);
        }

        Order order = new Order();

        order.setEmail(emailId);
        order.setOrderDate(LocalDate.now());

        order.setTotalAmount(cart.getTotalPrice());
        order.setOrderStatus("Order Accepted !");

        Payment payment = new Payment();
        payment.setOrder(order);
        payment.setPaymentMethod(paymentMethod);

        payment = paymentRepo.save(payment);
        order.setPayment(payment);

        Order savedOrder = orderRepo.save(order);

        List<CartItem> cartItems = cart.getCartItems();

        if (cartItems.isEmpty()) {
            throw new ApiException("Cart is empty");
        }

        List<OrderItem> orderItems = new ArrayList<>();

        for (CartItem cartItem : cartItems) {
            OrderItem orderItem = new OrderItem();

            orderItem.setProduct(cartItem.getProduct());
            orderItem.setQuantity(cartItem.getQuantity());
            orderItem.setDiscount(cartItem.getDiscount());
            orderItem.setOrderedProductPrice(cartItem.getProductPrice());
            orderItem.setOrder(savedOrder);

            orderItems.add(orderItem);
        }

        orderItems = orderItemRepo.saveAll(orderItems);

        cart.getCartItems().forEach(item -> {
            int quantity = item.getQuantity();

            Product product = item.getProduct();

            cartService.deleteProductFromCart(cartId, item.getProduct().getProductId());

            product.setQuantity(product.getQuantity() - quantity);
        });

        OrderDto orderDTO = modelMapper.map(savedOrder, OrderDto.class);

        orderItems.forEach(item -> orderDTO.getOrderItems().add(modelMapper.map(item, OrderItemDto.class)));

        return orderDTO;
    }

    @Override
    public OrderDto getOrder(String emailId, Long orderId) {

        Order order = orderRepo.findOrderByEmailAndOrderId(emailId, orderId);

        if (order == null) {
            throw new ResourceNotFoundException("Order", "orderId", orderId);
        }
        return modelMapper.map(order, OrderDto.class);
    }

    @Override
    public List<OrderDto> getOrdersByUser(String emailId) {

        List<Order> orders = orderRepo.findAllByEmail(emailId);

        if (orders.isEmpty()) {
            throw new ApiException("No orders placed yet by the user with email: " + emailId);
        }

        return orders.stream().map(order -> modelMapper.map(order, OrderDto.class))
            .toList();
    }

    @Override
    public OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder) {

        Sort sortByAndOrder = sortOrder.equalsIgnoreCase("asc") ? Sort.by(sortBy).ascending()
            : Sort.by(sortBy).descending();

        Pageable pageDetails = PageRequest.of(pageNumber, pageSize, sortByAndOrder);

        Page<Order> pageOrders = orderRepo.findAll(pageDetails);

        List<Order> orders = pageOrders.getContent();

        if (orders.isEmpty()) {
            throw new ApiException("No orders placed yet by the users");
        }

        List<OrderDto> orderDTOs = orders.stream().map(order -> modelMapper.map(order, OrderDto.class))
            .collect(Collectors.toList());

        OrderResponse orderResponse = new OrderResponse();

        orderResponse.setContent(orderDTOs);
        orderResponse.setPageNumber(pageOrders.getNumber());
        orderResponse.setPageSize(pageOrders.getSize());
        orderResponse.setTotalElements(pageOrders.getTotalElements());
        orderResponse.setTotalPages(pageOrders.getTotalPages());
        orderResponse.setLastPage(pageOrders.isLast());

        return orderResponse;
    }

    @Override
    public OrderDto updateOrder(String emailId, Long orderId, String orderStatus) {
        Order order = orderRepo.findOrderByEmailAndOrderId(emailId, orderId);

        if (order == null) {
            throw new ResourceNotFoundException("Order", "orderId", orderId);
        }

        order.setOrderStatus(orderStatus);

        return modelMapper.map(order, OrderDto.class);
    }
}
