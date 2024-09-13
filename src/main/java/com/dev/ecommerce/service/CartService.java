package com.dev.ecommerce.service;

import com.dev.ecommerce.payload.CartDto;
import java.util.List;

public interface CartService {

    CartDto addProductToCart(Long cartId, Long productId, Integer quantity);

    List<CartDto> getAllCarts();

    CartDto getCart(String emailId, Long cartId);

    CartDto updateProductQuantityInCart(Long cartId, Long productId, Integer quantity);

    void updateProductInCarts(Long cartId, Long productId);

    String deleteProductFromCart(Long cartId, Long productId);
}
