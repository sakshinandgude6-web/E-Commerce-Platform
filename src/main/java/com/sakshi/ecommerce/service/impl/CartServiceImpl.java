package com.sakshi.ecommerce.service.impl;

import com.sakshi.ecommerce.dto.AddToCartRequest;
import com.sakshi.ecommerce.entity.*;
import com.sakshi.ecommerce.repository.*;
import com.sakshi.ecommerce.service.CartService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CartServiceImpl implements CartService {

        private final CartRepository cartRepository;
        private final CartItemRepository cartItemRepository;
        private final ProductRepository productRepository;
        private final UserRepository userRepository;

        @Override
        public void addToCart(String email, AddToCartRequest request) {

                User user = userRepository.findByEmail(email)
                                .orElseThrow(() -> new RuntimeException("User not found"));

                Cart cart = cartRepository.findByUser(user).orElse(null);

                if (cart == null) {
                        cart = new Cart();
                        cart.setUser(user);
                        cart = cartRepository.save(cart);
                }

                Product product = productRepository.findById(request.getProductId())
                                .orElseThrow(() -> new RuntimeException("Product not found"));

                // Check if product already exists in cart
                CartItem existingItem = cartItemRepository
                                .findByCartAndProduct(cart, product)
                                .orElse(null);

                if (existingItem != null) {
                        // Update quantity
                        existingItem.setQuantity(existingItem.getQuantity() + request.getQuantity());
                        cartItemRepository.save(existingItem);
                } else {
                        CartItem item = CartItem.builder()
                                        .cart(cart)
                                        .product(product)
                                        .quantity(request.getQuantity())
                                        .price(product.getPrice())
                                        .build();

                        cartItemRepository.save(item);
                }
        }
}