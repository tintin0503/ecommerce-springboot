package com.dev.ecommerce.repositories;

import com.dev.ecommerce.entity.Cart;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CartRepository extends JpaRepository<Cart, Long> {

    @Query("select c from Cart c where c.user.email = ?1 and c.id = ?2")
    Cart findCartByEmailAndCartId(String email, Long cartId);

    @Query("select c from Cart c join fetch c.cartItems ci join fetch ci.product p where p.id = ?1")
    List<Cart> findCartsByProductId(Long productId);
}
