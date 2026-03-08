package com.sakshi.ecommerce.repository;

import com.sakshi.ecommerce.entity.Cart;
import com.sakshi.ecommerce.entity.CartItem;
import com.sakshi.ecommerce.entity.Product;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {
    Optional<CartItem> findByCartAndProduct(Cart cart, Product product);
}