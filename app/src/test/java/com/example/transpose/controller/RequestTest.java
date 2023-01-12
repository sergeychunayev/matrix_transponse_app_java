package com.example.transpose.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


@SpringBootTest
@AutoConfigureMockMvc
class RequestTest {

    @Autowired
    private MockMvc mockMvc;


    @Test
    void testBad() throws Exception {
        mockMvc.perform(
                post("/transpose")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(new int[][]{{1, 2, 3}, {4, 5}}))
        ).andExpectAll(
                status().isBadRequest(),
                content().string("Matrix must have same number of columns for all rows. Offending index: 1")
        );
    }

    @Test
    void testGood() throws Exception {
        mockMvc.perform(
                post("/transpose")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(new ObjectMapper().writeValueAsBytes(new int[][]{{1, 2, 3}, {4, 5, 6}}))
        ).andExpectAll(
                status().isOk(),
                content().json("[[1,4],[2,5],[3,6]]")
        );
    }

}
