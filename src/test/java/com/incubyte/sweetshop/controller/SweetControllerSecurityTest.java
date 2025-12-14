package com.incubyte.sweetshop.controller;

import com.incubyte.sweetshop.repository.SweetRepository;
import com.incubyte.sweetshop.security.JwtUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;


@WebMvcTest(SweetController.class)
class SweetControllerSecurityTest {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private SweetRepository sweetRepository;

    @MockBean
    private JwtUtil jwtUtil;


    @Test
    void shouldRejectGetSweetsWithoutJwtToken() throws Exception {
        mockMvc.perform(get("/api/sweets"))
                .andExpect(status().isUnauthorized());
    }

    @Test
    void shouldRejectDeleteSweetForUserRole() throws Exception {

        // mock valid token but USER role
        when(jwtUtil.validateToken(any())).thenReturn(true);
        when(jwtUtil.extractUsername(any())).thenReturn("jaswanth");
        when(jwtUtil.extractRole(any())).thenReturn("USER");

        mockMvc.perform(delete("/api/sweets/{id}", 1L)
                        .header("Authorization", "Bearer dummy-token"))
                .andExpect(status().isForbidden());
    }


}
