package com.ust.formacion.web_project_with_unit_test.controllers;


import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.RequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@WebMvcTest(controllers = MainController.class)
public class MainControllerTest {

    @Autowired
    private MockMvc mockMvc;

    //In this test we only launch the web layer so we can test our controller
    @Test
    public void testIsAlive() throws Exception {
        //here we want to call to an URL /alive and expect a response "UP"
        
        //create a get request that accepts JSON response
        RequestBuilder requestBuilder = MockMvcRequestBuilders.get("/alive").accept(MediaType.APPLICATION_JSON);
        
        //execute the request and return the result
        MvcResult mvcr = mockMvc.perform(requestBuilder)
                        .andExpect(status().isOk()) // we expect HTTP 200
                        .andExpect(content().string("UP"))
                        .andReturn();

        System.out.println("Response: " + mvcr.getResponse().getContentAsString());
        
        //verify the response
        //this is redundant due to the andExpect above, but just to show another way to assert the response
        assertEquals("UP", mvcr.getResponse().getContentAsString());
    }
}
