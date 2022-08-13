package com.myprojects.cadclients;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.server.LocalServerPort;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithUserDetails;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

@AutoConfigureMockMvc
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
class CadClientsApplicationTests {

    @Autowired
    private MockMvc mockMvc;

    @LocalServerPort
    private int port;
    private String baseUrl = "http://localhost:";

    @BeforeEach
    void setUp() {
        baseUrl += port + "/";
    }


    @Test
    public void statusPaginaInicial() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl).accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }

    @Test
    @WithUserDetails(value = "admin")
    public void deveTerAcessoParaTrazerListaDeClientes() throws Exception {
        mockMvc.perform(MockMvcRequestBuilders.get(baseUrl + "rest/clients/").accept(MediaType.ALL))
                .andExpect(MockMvcResultMatchers.status().isOk());
    }
}
