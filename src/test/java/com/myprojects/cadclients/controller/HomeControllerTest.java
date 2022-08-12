package com.myprojects.cadclients.controller;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;

import com.myprojects.cadclients.service.ClientService;

@ActiveProfiles(value = "test")
@WebMvcTest(controllers = HomeController.class)
public class HomeControllerTest {

    @MockBean
    private ClientService clientService;
    @Autowired
    private MockMvc mockMvc;

    @Test
    void deveCarregarPaginaInicial() throws Exception {

        mockMvc.perform(get("/index")).andExpect(status().isOk());
    }
    @Test
    void deveCarregarListaDeClientes() throws Exception {

        mockMvc.perform(get("/listarClientes")).andExpect(status().isOk());
    }
}