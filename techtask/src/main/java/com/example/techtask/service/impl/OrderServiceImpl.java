package com.example.techtask.service.impl;

import com.example.techtask.model.*;

import com.example.techtask.service.*;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private EntityManager entityManager;


    @Autowired
    public OrderServiceImpl(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Order findOrder() {

        String sql = """

            select o from Order o
            where o.quantity>1
            ORDER BY o.createdAt DESC
            LIMIT 1

""";
        TypedQuery<Order> query = entityManager.createQuery(sql, Order.class);

        try {
            return  query.getSingleResult();
        } catch (NoResultException e) {
            return new Order();
        }

    }

    @Override
    public List<Order> findOrders() {

        String sql = """
            select o from Order o
            join User u on u.id = o.userId
            where u.userStatus = 'ACTIVE'
            ORDER BY o.createdAt
""";

        TypedQuery<Order> query = entityManager.createQuery(sql, Order.class);

        try {
            return  query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }
    }
}
