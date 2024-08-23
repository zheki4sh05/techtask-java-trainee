package com.example.techtask.controller;

import com.example.techtask.model.*;

import com.example.techtask.service.impl.*;
import com.fasterxml.jackson.core.type.*;
import com.fasterxml.jackson.databind.*;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.test.autoconfigure.web.servlet.*;
import org.springframework.boot.test.context.*;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.*;
import org.springframework.test.web.servlet.request.*;
import org.springframework.test.web.servlet.result.*;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;


@SpringBootTest
@AutoConfigureMockMvc(printOnlyOnFailure = false)
class UserControllerTest {

    @Autowired
    MockMvc mockMvc;


    @Autowired
    UserServiceImpl userService;

    @Autowired
    ObjectMapper objectMapper;


    @Test
    void handleGetUser_ReturnsValidResponse() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/api/v1/users/desired-user");

        var result = mockMvc.perform(requestBuilder)
                .andExpectAll(

                        MockMvcResultMatchers.status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)


                );

        String resultStr = result.andReturn().getResponse().getContentAsString();

        User user = objectMapper.readValue(resultStr, User.class);

        assertAll(
                ()-> assertNotEquals( new User(), user),
                ()-> assertNotEquals(null, user),
                ()-> assertNotEquals( null, userService.findUser()),
                ()->assertNotEquals( new User(), userService.findUser())
        );
    }

    @Test
    void handleGetUsers_ReturnsValidResponse() throws Exception{
        var requestBuilder = MockMvcRequestBuilders.get("http://localhost:8080/api/v1/users/desired-users");

        var result = mockMvc.perform(requestBuilder)
                .andDo(print())
                .andExpectAll(
                        MockMvcResultMatchers.status().isOk(),
                        content().contentType(MediaType.APPLICATION_JSON)
                );

        String resultStr = result.andReturn().getResponse().getContentAsString();
        List<User> userList = objectMapper.readValue(resultStr, new TypeReference<>() {});


        assertAll(
                ()->assertNotEquals( null, userService.findUsers()),
                ()->assertNotEquals(0,userService.findUsers().size()),
                ()->assertNotEquals( null, userList),
                ()->assertNotEquals(0,userList.size())
        );
    }

}