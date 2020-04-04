package com.aoher.springsecuritytaglibs;

import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.jupiter.api.Assertions.*;

@RunWith(SpringRunner.class)
@SpringBootTest(
        webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT,
        classes = SpringBootSecurityTagLibsApplication.class
)
class SpringBootSecurityTagLibsApplicationTest {

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    void whenUserIsAuthenticatedThenAuthenticatedSectionsShowOnSite() {
        String body = restTemplate.withBasicAuth("testUser", "password")
                .getForEntity("/", String.class)
                .getBody();

        assertNotNull(body);
        assertFalse(body.contains("Login"));
        assertTrue(body.contains("Logout"));
        assertTrue(body.contains("Manage Users"));
        assertTrue(body.contains("testUser"));
        assertTrue(body.contains("<a href=\"userManagement\">"));
        assertTrue(body.contains("<input type=\"hidden\" name=\"_csrf\" value=\""));
        assertTrue(body.contains("<meta name=\"_csrf_parameter\" content=\"_csrf\" />"));
    }

    @Test
    void whenUserIsNotAuthenticatedThenOnlyAnonymousSectionsShowOnSite() {
        String body = restTemplate.getForEntity("/", String.class)
                .getBody();

        assertNotNull(body);
        assertTrue(body.contains("Login"));
        assertFalse(body.contains("Logout"));
    }
}