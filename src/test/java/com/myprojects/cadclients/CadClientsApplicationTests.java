package com.myprojects.cadclients;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.web.SecurityFilterChain;

import com.myprojects.cadclients.service.ClientService;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CadClientsApplicationTests {

    @LocalServerPort
    private int port;
    private static TestRestTemplate testRestTemplate;
    private String baseUrl = "http://localhost:";

    @MockBean
    private ClientService clientService;
    @MockBean
    private SecurityFilterChain filterChain;

    @BeforeAll
    static void beforeAll() {
        testRestTemplate = new TestRestTemplate();
    }

    @BeforeEach
    void setUp() {
        baseUrl += port + "/";
    }

    @Test
    public void statusPaginaInicial() throws Exception {

        ResponseEntity<String> response = testRestTemplate
                .withBasicAuth("admin", "$2a$10$9/Sj7fzfbcfT/2i3UNNdMuHQr01WPBtQyR.bB09WP3ZU6YxIVMRsG")
                .getForEntity(baseUrl, String.class);
        assertEquals(HttpStatus.OK, response.getStatusCode());
    }
}
