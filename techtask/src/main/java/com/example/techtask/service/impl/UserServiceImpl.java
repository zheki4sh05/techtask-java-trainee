package com.example.techtask.service.impl;

import com.example.techtask.model.*;

import com.example.techtask.service.*;
import jakarta.persistence.*;

import org.springframework.beans.factory.annotation.*;


import org.springframework.stereotype.*;

import java.util.*;
@Service
public class UserServiceImpl implements UserService {


    @Autowired
    private EntityManager entityManager;
    @Override
    public User findUser() {

        String sql = """
                
                SELECT u
                FROM User u
                         JOIN Order o ON u.id = o.userId
                WHERE YEAR(o.createdAt) = 2003
                GROUP BY u.id, u.email
                ORDER BY SUM(o.price * o.quantity) DESC
                LIMIT 1
                """;

        TypedQuery<User> query = entityManager.createQuery(sql, User.class);

        try {
            return  query.getSingleResult();
        } catch (NoResultException e) {
            return new User();
        }

    }

    @Override
    public List<User> findUsers() {

        String sql = """
            select u
             from User u
            join Order o on u.id = o.userId
            where YEAR(o.createdAt) = 2010 and o.orderStatus='PAID'
""";

        TypedQuery<User> query = entityManager.createQuery(sql, User.class);

        try {
            return  query.getResultList();
        } catch (NoResultException e) {
            return new ArrayList<>();
        }

    }
}
