package com.aoher.integrationtesting.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(SecuredController.class)
public class SecuredControllerWebMvcIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Test
    public void givenRequestOnPrivateService_shouldFailWith401() throws Exception {
        mockMvc.perform(
                get("/private/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isUnauthorized());
    }

    @Test
    @WithMockUser(value = "spring")
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() throws Exception {
        mockMvc.perform(
                get("/private/hello")
                        .contentType(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

}