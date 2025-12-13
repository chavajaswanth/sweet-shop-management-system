package com.incubyte.sweetshop.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.sweetshop.domain.User;
import com.incubyte.sweetshop.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import com.jayway.jsonpath.JsonPath;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(AuthController.class)
class AuthControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private UserRepository userRepository;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void shouldRegisterNewUser() throws Exception {
        User savedUser = new User("jaswanth", "encodedPass", "USER");
        when(userRepository.save(any(User.class))).thenReturn(savedUser);

        String requestBody = """
        {
          "username": "jaswanth",
          "password": "password123"
        }
        """;

        mockMvc.perform(post("/api/auth/register")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.username").value("jaswanth"))
                .andExpect(jsonPath("$.role").value("USER"));
    }

    @Test
    void shouldLoginUserAndReturnJwtToken() throws Exception {

        String requestBody = """
        {
          "username": "jaswanth",
          "password": "password123"
        }
        """;

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists());
    }

    @Test
    void shouldLoginAndReturnJwtToken() throws Exception {

        String requestBody = """
    {
      "username": "jaswanth",
      "password": "password123"
    }
    """;

        mockMvc.perform(post("/api/auth/login")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.token").exists())
                .andExpect(jsonPath("$.token").isNotEmpty())
                // JWT format check → 3 dot-separated parts
                .andExpect(result -> {
                    String json = result.getResponse().getContentAsString();
                    String token = JsonPath.read(json, "$.token");
                    assertThat(token.split("\\.").length).isEqualTo(3);
                });
    }


}
