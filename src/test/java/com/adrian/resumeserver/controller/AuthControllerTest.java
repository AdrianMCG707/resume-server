package com.adrian.resumeserver.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.transaction.annotation.Transactional;

import java.util.Map;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@SpringBootTest
@AutoConfigureMockMvc
@Transactional
public class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void register_shouldReturn201AndToken() throws Exception {
        Map<String, String> request = Map.of(
                "email", "newuser@test.com",
                "password", "password123"
        );

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void register_shouldReturn409_whenEmailAlreadyExists() throws Exception {
        Map<String, String> request = Map.of(
                "email", "duplicate@test.com",
                "password", "password123"
        );

        // First registration
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // Second registration with same email
        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isConflict());
    }

    @Test
    void login_shouldReturn200AndToken() throws Exception {
        // Register first
        Map<String, String> request = Map.of(
                "email", "loginuser@test.com",
                "password", "password123"
        );

        mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isCreated());

        // Then login
        mockMvc.perform(post("/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(request)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void protectedRoute_shouldReturn403_withNoToken() throws Exception {
        mockMvc.perform(post("/resume/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content("{\"email\":\"a@b.com\",\"fullName\":\"Test\"}"))
                .andExpect(status().isForbidden());
    }

    @Test
    void protectedRoute_shouldReturn201_withValidToken() throws Exception {
        // Register and get token
        Map<String, String> authRequest = Map.of(
                "email", "tokenuser@test.com",
                "password", "password123"
        );

        MvcResult result = mockMvc.perform(post("/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(authRequest)))
                .andExpect(status().isCreated())
                .andReturn();

        String responseBody = result.getResponse().getContentAsString();
        String token = objectMapper.readTree(responseBody).get("token").asText();

        // Use token to hit protected route
        Map<String, String> userRequest = Map.of(
                "email", "tokenuser@test.com",
                "fullName", "Token User",
                "title", "Engineer",
                "location", "CA",
                "summary", "Test user"
        );

        mockMvc.perform(post("/resume/users")
                        .contentType(MediaType.APPLICATION_JSON)
                        .header("Authorization", "Bearer " + token)
                        .content(objectMapper.writeValueAsString(userRequest)))
                .andExpect(status().isCreated());
    }
}