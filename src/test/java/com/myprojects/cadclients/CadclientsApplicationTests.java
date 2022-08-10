package com.myprojects.cadclients;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.net.URL;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CadclientsApplicationTests {

	@LocalServerPort
	private int port;

	private String baseUrl = "http://localhost:";

	@Autowired
	private TestRestTemplate testRestTemplate;

	@Test
	public void statusPaginaInicial() throws Exception {
		URL url = new URL(baseUrl + port + "/");
		ResponseEntity<String> response = testRestTemplate.getForEntity(url.toString(), String.class);
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

}
