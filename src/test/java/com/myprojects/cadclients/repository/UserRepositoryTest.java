package com.myprojects.cadclients.repository;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.myprojects.cadclients.model.UserModel;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void deveRetornarPesquisaPorNomeDoUsuario() {

        String username = "admin";
        Optional<UserModel> optUM = userRepository.findByUsername(username);
        assertTrue(optUM.isPresent());
    }
}