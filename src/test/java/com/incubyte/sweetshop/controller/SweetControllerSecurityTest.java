package com.incubyte.sweetshop.controller;

import com.incubyte.sweetshop.repository.SweetRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@WebMvcTest(SweetController.class)
class SweetControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SweetRepository sweetRepository;

    @Test
    void shouldRejectGetSweetsWithoutJwtToken() throws Exception {
        mockMvc.perform(get("/api/sweets"))
                .andExpect(status().isUnauthorized());
    }
}
