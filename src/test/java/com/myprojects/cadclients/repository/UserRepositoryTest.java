package com.myprojects.cadclients.repository;

import com.myprojects.cadclients.model.UserModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@SpringBootTest
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    void deveRetornarPesquisaPorNomeDoUsuario() {

        String username = "admin";
        Optional<UserModel> userModel = userRepository.findByUsername(username);
        assertThat(userModel.isPresent()).isTrue();
    }
}