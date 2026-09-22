package com.jorge.course.antigravity.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(IndexController.class)
class IndexControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    void shouldReturnHolaMundoAndUserOnRoot() throws Exception {
        mockMvc.perform(get("/api/index"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("hola mundo desde spring boot"))
                .andExpect(jsonPath("$.user.name").value("Jorge"))
                .andExpect(jsonPath("$.user.lastname").value("Doe"));
    }

    @Test
    void shouldReturnUserOnGreeting() throws Exception {
        mockMvc.perform(get("/api/greeting"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.message").value("hola mundo desde spring boot"))
                .andExpect(jsonPath("$.user.name").value("Jorge"))
                .andExpect(jsonPath("$.user.lastname").value("Doe"));
    }

    @Test
    void shouldReturnUserDetails() throws Exception {
        mockMvc.perform(get("/api/details"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Jorge"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("[EMAIL_ADDRESS]"));
    }

}
