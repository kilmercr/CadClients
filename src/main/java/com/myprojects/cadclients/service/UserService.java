package com.myprojects.cadclients.service;

import java.util.Collection;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.myprojects.cadclients.model.UserModel;
import com.myprojects.cadclients.repository.UserRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class UserService {

    private final UserRepository userRepository;

    public Collection<UserModel> findAll() {
        return userRepository.findAll();
    }

    public Optional<UserModel> findById(Long id) {
        return userRepository.findById(id);
    }

    public Optional<UserModel> findByUsername(String username) {
        return userRepository.findByUsername(username);
    }
}
