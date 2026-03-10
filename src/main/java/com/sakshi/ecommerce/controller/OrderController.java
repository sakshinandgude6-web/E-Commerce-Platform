package com.sakshi.ecommerce.controller;

import com.sakshi.ecommerce.entity.Order;
import com.sakshi.ecommerce.service.OrderService;
import lombok.RequiredArgsConstructor;

import java.util.List;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/orders")
@RequiredArgsConstructor
public class OrderController {

    private final OrderService orderService;

    @PostMapping("/checkout")
    public String checkout(Authentication authentication) {

        String email = authentication.getName();

        orderService.checkout(email);

        return "Order placed successfully";
    }

    @GetMapping
    public List<Order> getMyOrders(Authentication authentication) {

        String email = authentication.getName();

        return orderService.getUserOrders(email);
    }
}