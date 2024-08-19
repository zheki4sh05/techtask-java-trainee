package com.example.techtask.service.impl;

import com.example.techtask.model.*;
import com.example.techtask.repository.*;
import com.example.techtask.service.*;
import org.hibernate.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.jdbc.core.*;
import org.springframework.orm.hibernate5.*;
import org.springframework.stereotype.*;

import java.sql.*;
import java.util.*;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User findUser() {

        User user = userRepository.findUserByOrderYearAndTotalPrice();

        return user!=null ? user : new User();

    }

    @Override
    public List<User> findUsers() {
        List<User> userList = userRepository.findUsersByOrderYearAndStatus();

        return userList !=null ? userList : new ArrayList<User>();

    }
}
