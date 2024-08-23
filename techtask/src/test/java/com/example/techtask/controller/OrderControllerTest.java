package com.example.techtask.controller;
import com.example.techtask.model.Order;
import com.example.techtask.service.impl.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.*;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;

@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class OrderControllerTest {

    @Autowired
    MockMvc mockMvc;

    @Autowired
    OrderServiceImpl orderService;

    @Autowired
    ObjectMapper objectMapper;

    @Test
    void handleGetOrder_ReturnsValidResponse() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/api/v1/orders/desired-order");

        var result = mockMvc.perform(requestBuilder)
                .andExpectAll(

                        MockMvcResultMatchers.status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)


                );

        String resultStr = result.andReturn().getResponse().getContentAsString();

        Order order = objectMapper.readValue(resultStr, Order.class);

        assertAll(
                ()->assertNotEquals( new Order(), order),
                ()->assertNotEquals(null, order),
                ()->assertNotEquals( null, orderService.findOrder()),
                ()->assertNotEquals( new Order(), orderService.findOrder())
        );
    }

    @Test
    void handleGetOrders_ReturnsValidResponse() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/api/v1/orders/desired-orders");

        var result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );

        String resultStr = result.andReturn().getResponse().getContentAsString();
        List<Order> orderList = objectMapper.readValue(resultStr, new TypeReference<>() {});


        assertAll(
                ()-> assertNotEquals( null, orderService.findOrders()),
                ()-> assertNotEquals(0,orderService.findOrders().size()),
                ()-> assertNotEquals( null, orderList),
                ()-> assertNotEquals(0,orderList.size())


        );
    }


}