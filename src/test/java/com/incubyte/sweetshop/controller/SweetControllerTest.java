package com.incubyte.sweetshop.controller;

import java.util.List;
import java.util.Optional;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.incubyte.sweetshop.domain.Sweet;
import com.incubyte.sweetshop.repository.SweetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(SweetController.class)
class SweetControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private ObjectMapper objectMapper;

    @MockBean
    private SweetRepository sweetRepository;

    @Test
    void shouldCreateSweet() throws Exception {
        Sweet savedSweet = new Sweet("Ladoo", "Indian", 10.0, 20);

        when(sweetRepository.save(any(Sweet.class))).thenReturn(savedSweet);

        String requestBody = """
            {
              "name": "Ladoo",
              "category": "Indian",
              "price": 10.0,
              "quantity": 20
            }
            """;

        mockMvc.perform(post("/api/sweets")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.name").value("Ladoo"))
                .andExpect(jsonPath("$.category").value("Indian"))
                .andExpect(jsonPath("$.price").value(10.0))
                .andExpect(jsonPath("$.quantity").value(20));
    }

    @Test
    void shouldReturnAllSweets() throws Exception {
        Sweet sweet1 = new Sweet("Ladoo", "Indian", 10.0, 20);
        Sweet sweet2 = new Sweet("Barfi", "Indian", 15.0, 10);

        when(sweetRepository.findAll()).thenReturn(List.of(sweet1, sweet2));

        mockMvc.perform(get("/api/sweets"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(2))
                .andExpect(jsonPath("$[0].name").value("Ladoo"))
                .andExpect(jsonPath("$[1].name").value("Barfi"));
    }

    @Test
    void shouldSearchSweetsByName() throws Exception {
        Sweet sweet = new Sweet("Ladoo", "Indian", 10.0, 20);

        when(sweetRepository.findByNameContainingIgnoreCase("lad"))
                .thenReturn(List.of(sweet));

        mockMvc.perform(get("/api/sweets/search")
                        .param("name", "lad"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(1))
                .andExpect(jsonPath("$[0].name").value("Ladoo"));
    }

    @Test
    void shouldUpdateSweetById() throws Exception {
        Sweet existing = new Sweet("Ladoo", "Indian", 10.0, 20);

        when(sweetRepository.findById(1L)).thenReturn(Optional.of(existing));
        when(sweetRepository.save(any(Sweet.class))).thenReturn(existing);

        String requestBody = """
        {
          "name": "Ladoo Deluxe",
          "category": "Indian",
          "price": 12.0,
          "quantity": 25
        }
        """;

        mockMvc.perform(put("/api/sweets/{id}", 1L)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(requestBody))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.name").value("Ladoo Deluxe"))
                .andExpect(jsonPath("$.price").value(12.0))
                .andExpect(jsonPath("$.quantity").value(25));
    }



}
