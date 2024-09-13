package com.dev.ecommerce.service.impl;

import com.dev.ecommerce.payload.CartDto;
import com.dev.ecommerce.service.CartService;
import jakarta.transaction.Transactional;
import java.util.List;
import org.springframework.stereotype.Service;

@Service
@Transactional
public class CartServiceImpl implements CartService {

    @Override
    public CartDto addProductToCart(Long cartId, Long productId, Integer quantity) {
        return null;
    }

    @Override
    public List<CartDto> getAllCarts() {
        return null;
    }

    @Override
    public CartDto getCart(String emailId, Long cartId) {
        return null;
    }

    @Override
    public CartDto updateProductQuantityInCart(Long cartId, Long productId, Integer quantity) {
        return null;
    }

    @Override
    public void updateProductInCarts(Long cartId, Long productId) {

    }

    @Override
    public String deleteProductFromCart(Long cartId, Long productId) {
        return null;
    }
}
