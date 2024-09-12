package com.dev.ecommerce.repositories;

import com.dev.ecommerce.entity.CartItem;
import com.dev.ecommerce.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    @Query("select ci.product from CartItem ci where ci.product.id = ?1")
    Product findProductById(Long productId);

    @Query("select ci from CartItem ci where ci.cart.id = ?1 and ci.product.id = ?2")
    CartItem findCartItemByProductIdAndCartId(Long cartId, Long productId);

    @Modifying
    @Query("delete from CartItem ci where ci.cart.id = ?1 and ci.product.id =  ?2")
    void deleteCartItemByProductIdAndCartId(Long productId, Long cartId);
}
