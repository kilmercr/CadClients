package com.myprojects.cadclients.repository;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Collection;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myprojects.cadclients.model.ClientModel;

@SpringBootTest
class ClientRepositoryTest {

    @Autowired
    private ClientRepository clientRepository;

    @Test
    void deveRetornarUmClientePesquisandoPorCpf() {
        String cpf = "33007748534";
        Optional<ClientModel> optCM = clientRepository.findByCpf(cpf);
        assertTrue(optCM.isPresent());
    }

    @Test
    void deveRetornarUmClientePesquisandoPorNome() {
        String nome = "Client no. 1";
        Optional<ClientModel> optCM = clientRepository.findByName(nome);
        assertTrue(optCM.isPresent());
    }

    @Test
    void deveRetornarUmaListaDeClientesPesquisandoPorParteDoNome() {
        String nome = "Client";
        Collection<ClientModel> lstCM = clientRepository.findByNameContaining(nome);
        assertFalse(lstCM.isEmpty());
    }
}