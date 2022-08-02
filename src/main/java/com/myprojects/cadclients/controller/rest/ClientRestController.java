package com.myprojects.cadclients.controller.rest;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Optional;

import javax.validation.Valid;

import org.springframework.beans.BeanUtils;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.myprojects.cadclients.dtos.ClientDto;
import com.myprojects.cadclients.model.ClientModel;
import com.myprojects.cadclients.service.ClientService;
import com.myprojects.cadclients.utils.StringUtils;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@RestController
@RequestMapping("rest/clients")
public class ClientRestController {

    private final ClientService clientService;

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping
    public ResponseEntity<Object> getAllClients() {
        return ResponseEntity.ok(clientService.findAll());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/{id}")
    public ResponseEntity<Object> getClientById(@PathVariable(name = "id") Long id) {
        Optional<ClientModel> optCM = clientService.findById(id);
        if (!optCM.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client, com o id [ " + id + " ], não existe!");
        }
        return ResponseEntity.ok(optCM.get());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/cpf/{cpf}")
    public ResponseEntity<Object> getClientById(@PathVariable(name = "cpf") String cpf) {
        Optional<ClientModel> optCM = clientService.findByCpf(cpf);
        if (!optCM.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client, com o CPF [ " + cpf + " ], não existe!");
        }
        return ResponseEntity.ok(optCM.get());
    }

    @PreAuthorize("hasAnyRole('ROLE_ADMIN', 'ROLE_USER')")
    @GetMapping("/name/{name}")
    public ResponseEntity<Object> getClientByName(@PathVariable(name = "name") String name) {
        Optional<ClientModel> optCM = clientService.findByName(name);
        if (!optCM.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Client não existente com o nome [ " + name + " ].");
        }
        return ResponseEntity.ok(optCM.get());
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PostMapping
    public ResponseEntity<Object> saveClient(@RequestBody @Valid ClientDto clientDto) {
        clientDto.setCpf(StringUtils.clearCPF(clientDto.getCpf()));
        Optional<ClientModel> optCM = clientService.findByCpf(clientDto.getCpf());
        if (optCM.isPresent()) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Já existe um cliente com este CPF!");
        }

        ClientModel cm = new ClientModel();
        BeanUtils.copyProperties(clientDto, cm);
        cm.setDtCreate(LocalDateTime.now(ZoneId.systemDefault()).withNano(0));
        return ResponseEntity.status(HttpStatus.CREATED).body(clientService.save(cm));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @PutMapping("/{id}")
    public ResponseEntity<Object> updateClient(@PathVariable(name = "id") Long id,
            @RequestBody @Valid ClientDto clientDto) {
        clientDto.setCpf(StringUtils.clearCPF(clientDto.getCpf()));
        Optional<ClientModel> optCM = clientService.findById(id);
        if (!optCM.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client, com o id [ " + id + " ], não existe!");
        }

        if (!optCM.get().getCpf().equals(clientDto.getCpf())) {
            Optional<ClientModel> optCMbyCpf = clientService.findByCpf(clientDto.getCpf());
            if (optCMbyCpf.isPresent()) {
                return ResponseEntity.status(HttpStatus.CONFLICT).body("Conflito: Já existe um cliente com este CPF!");
            }
        }

        ClientModel cm = new ClientModel();
        BeanUtils.copyProperties(clientDto, cm);
        cm.setClientId(optCM.get().getClientId());
        cm.setDtCreate(optCM.get().getDtCreate());
        cm.setDtUpdate(LocalDateTime.now(ZoneId.systemDefault()).withNano(0));
        return ResponseEntity.ok(clientService.save(cm));
    }

    @PreAuthorize("hasRole('ROLE_ADMIN')")
    @DeleteMapping("/{id}")
    public ResponseEntity<Object> deleteClient(@PathVariable(name = "id") Long id) {
        Optional<ClientModel> optCM = clientService.findById(id);
        if (!optCM.isPresent()) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Client, com o id [ " + id + " ], não existe!");
        }

        clientService.delete(optCM.get());
        return ResponseEntity.ok("Cliente removido com sucesso!");
    }

}
