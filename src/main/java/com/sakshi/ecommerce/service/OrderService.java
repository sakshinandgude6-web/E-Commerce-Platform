package com.sakshi.ecommerce.service;

import java.util.List;

import com.sakshi.ecommerce.entity.Order;

public interface OrderService {

    void checkout(String email);

    List<Order> getUserOrders(String email);

}