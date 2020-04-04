package com.aoher.integrationtesting.controller;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.assertEquals;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class SecuredControllerRestTemplateIntegrationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void givenRequestOnPrivateService_shouldFailWith401() {
        ResponseEntity<String> response = restTemplate.getForEntity("/private/hello", String.class);
        assertEquals(HttpStatus.UNAUTHORIZED, response.getStatusCode());
    }

    @Test
    public void givenAuthRequestOnPrivateService_shouldSucceedWith200() {
        ResponseEntity<String> response = restTemplate.withBasicAuth("spring", "secret")
                .getForEntity("/private/hello", String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
