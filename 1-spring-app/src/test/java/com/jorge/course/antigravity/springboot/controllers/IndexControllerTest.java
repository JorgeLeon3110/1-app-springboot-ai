package com.jorge.course.antigravity.springboot.controllers;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.webmvc.test.autoconfigure.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.xpath;

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
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.message").value("hola mundo desde spring boot"))
                .andExpect(jsonPath("$.user.name").value("Jorge"))
                .andExpect(jsonPath("$.user.lastname").value("Doe"));
    }

    @Test
    void shouldReturnUserDetails() throws Exception {
        mockMvc.perform(get("/api/details").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Jorge"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("[EMAIL_ADDRESS]"));
    }

    @Test
    void shouldReturnUserEndpoint() throws Exception {
        mockMvc.perform(get("/api/user").accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isAccepted())
                .andExpect(jsonPath("$.name").value("Jorge"))
                .andExpect(jsonPath("$.lastname").value("Doe"))
                .andExpect(jsonPath("$.email").value("[EMAIL_ADDRESS]"));
    }

    @Test
    void shouldReturnUserAsPlainTextWithAcceptHeader() throws Exception {
        mockMvc.perform(get("/api/details").accept(MediaType.TEXT_PLAIN))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("User [name=Jorge, lastname=Doe, email=[EMAIL_ADDRESS]]"));
    }

    @Test
    void shouldReturnUserAsPlainTextOnTextEndpoint() throws Exception {
        mockMvc.perform(get("/api/user-text"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_PLAIN))
                .andExpect(content().string("User [name=Jorge, lastname=Doe, email=[EMAIL_ADDRESS]]"));
    }

    @Test
    void shouldReturnUserAsXmlOnXmlEndpoint() throws Exception {
        mockMvc.perform(get("/api/user-xml"))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
                .andExpect(xpath("/User/name").string("Jorge"))
                .andExpect(xpath("/User/lastname").string("Doe"))
                .andExpect(xpath("/User/email").string("[EMAIL_ADDRESS]"));
    }

    @Test
    void shouldReturnUserAsXmlWithAcceptHeader() throws Exception {
        mockMvc.perform(get("/api/details").accept(MediaType.APPLICATION_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.APPLICATION_XML))
                .andExpect(xpath("/User/name").string("Jorge"))
                .andExpect(xpath("/User/lastname").string("Doe"))
                .andExpect(xpath("/User/email").string("[EMAIL_ADDRESS]"));
    }

}
