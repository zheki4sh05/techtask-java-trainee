package com.example.techtask.service.impl;

import com.example.techtask.model.*;
import com.example.techtask.repository.*;
import com.example.techtask.service.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {


    private final OrderRepository orderRepository;


    @Autowired
    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public Order findOrder() {

        Order order = orderRepository.findByQuantityAndDate();

        return  order!=null ? order : new Order();
    }

    @Override
    public List<Order> findOrders() {

        List<Order> orders = orderRepository.findAllByStatusAndDate();
        return orders!=null ? orders : new ArrayList<Order>();
    }
}
