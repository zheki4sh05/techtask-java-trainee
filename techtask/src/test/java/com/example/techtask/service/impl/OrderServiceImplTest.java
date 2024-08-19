package com.example.techtask.service.impl;

import com.example.techtask.model.*;
import com.example.techtask.model.Order;
import com.example.techtask.repository.*;
import org.junit.jupiter.api.*;
import org.junit.jupiter.api.extension.*;
import org.mockito.*;
import org.mockito.junit.jupiter.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
@ExtendWith(MockitoExtension.class)
class OrderServiceImplTest {

    @Mock
    OrderRepository orderRepository;

    @InjectMocks
    OrderServiceImpl orderService;

    @Test
    void handleGetOrder(){

        Order order = new Order();

        Mockito.doReturn(order).when(orderRepository).findByQuantityAndDate();

        Order foundOrder = orderService.findOrder();

        assertNotNull(foundOrder);

        assertEquals(foundOrder, order);

    }

    @Test
    void handleGetNullOrder(){

        Order emptyOrder = new Order();

        Mockito.doReturn(null).when(orderRepository).findByQuantityAndDate();

        Order foundOrder = orderService.findOrder();

        assertNotNull(foundOrder);

        assertEquals(foundOrder, emptyOrder);

    }

    @Test
    void handleGetAllOrders(){
        List<Order> orders = new ArrayList<>();

        Mockito.doReturn(orders).when(orderRepository).findAllByStatusAndDate();

        List<Order> foundList = orderService.findOrders();

        assertNotNull(foundList);

        assertEquals(foundList, orders);

    }

    @Test
    void handleGetAllNullOrders(){


        Mockito.doReturn(null).when(orderRepository).findAllByStatusAndDate();

        List<Order> foundList = orderService.findOrders();

        assertNotNull(foundList);

        assertEquals(foundList.size(), 0);

    }

}