package com.myprojects.cadclients.controller.rest;

import java.util.Collection;
import java.util.Optional;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.cadclients.model.UserModel;
import com.myprojects.cadclients.service.UserService;

@RestController
@RequestMapping("rest/users")
public class UserRestController {

    private final UserService userService;

    public UserRestController(UserService userService) {
        this.userService = userService;
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping
    public ResponseEntity<Collection<UserModel>> findAll() {
        return ResponseEntity.ok(userService.findAll());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> findById(@PathVariable(name = "id") Long id) {
        Optional<UserModel> optUM = userService.findById(id);
        if (!optUM.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Usuário com o id [ " + id + " ] não encontrado!");
        return ResponseEntity.ok(optUM.get());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @GetMapping("/name/{username}")
    public ResponseEntity<Object> findByUsername(@PathVariable(name = "username") String username) {
        Optional<UserModel> optUM = userService.findByUsername(username);
        if (!optUM.isPresent())
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Usuário com o username [ " + username + " ] não encontrado!");
        return ResponseEntity.ok(optUM.get());
    }
}
