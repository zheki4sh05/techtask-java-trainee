package com.example.techtask.service.impl;

import com.example.techtask.model.*;
import com.example.techtask.repository.*;
import com.example.techtask.service.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;


@ExtendWith(MockitoExtension.class)
class UserServiceImplTest {

    @Mock
    UserRepository userRepository;

    @InjectMocks
    UserServiceImpl userService;

    @Test
    void handleGetUser(){

        User user = new User();

        Mockito.doReturn(user).when(userRepository).findUserByOrderYearAndTotalPrice();

        User foundUser = userService.findUser();

        assertNotNull(foundUser);

        assertEquals(foundUser, user);

    }

    @Test
    void handleGetNullUser(){

        User emptyUser = new User();

        Mockito.doReturn(null).when(userRepository).findUserByOrderYearAndTotalPrice();

        User foundUser = userService.findUser();

        assertNotNull(foundUser);

        assertEquals(foundUser, emptyUser);

    }

    @Test
    void handleGetAllUsers(){
        List<User> userList = new ArrayList<>();

        Mockito.doReturn(userList).when(userRepository).findUsersByOrderYearAndStatus();

        List<User> foundList = userService.findUsers();

        assertNotNull(foundList);

        assertEquals(foundList, userList);

    }

    @Test
    void handleGetAllNullUsers(){


        Mockito.doReturn(null).when(userRepository).findUsersByOrderYearAndStatus();

        List<User> foundList = userService.findUsers();

        assertNotNull(foundList);

        assertEquals(foundList.size(), 0);

    }

}