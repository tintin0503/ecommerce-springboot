package com.dev.ecommerce.service;

import com.dev.ecommerce.payload.OrderDto;
import com.dev.ecommerce.payload.response.OrderResponse;
import java.util.List;

public interface OrderService {

    OrderDto placeOrder(String emailId, Long cartId, String paymentMethod);

    OrderDto getOrder(String emailId, Long orderId);

    List<OrderDto> getOrdersByUser(String emailId);

    OrderResponse getAllOrders(Integer pageNumber, Integer pageSize, String sortBy, String sortOrder);

    OrderDto updateOrder(String emailId, Long orderId, String orderStatus);
}
