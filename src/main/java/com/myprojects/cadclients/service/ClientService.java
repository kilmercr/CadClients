package com.myprojects.cadclients.service;

import java.util.Collection;
import java.util.Optional;

import javax.transaction.Transactional;

import org.springframework.stereotype.Service;

import com.myprojects.cadclients.model.ClientModel;
import com.myprojects.cadclients.repository.ClientRepository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class ClientService {

    private final ClientRepository clientRepository;

    public Collection<ClientModel> findAll() {
        return clientRepository.findAll();
    }

    public Optional<ClientModel> findById(Long clientId) {
        return clientRepository.findById(clientId);
    }

    public Optional<ClientModel> findByCpf(String cpf) {
        return clientRepository.findByCpf(cpf);
    }

    public Optional<ClientModel> findByName(String name) {
        return clientRepository.findByName(name);
    }

    public Collection<ClientModel> findByNameContaining(String name) {
        return clientRepository.findByNameContaining(name);
    }

    @Transactional
    public ClientModel save(ClientModel clientModel) {
        return clientRepository.save(clientModel);
    }

    @Transactional
    public void delete(ClientModel clientModel) {
        clientRepository.delete(clientModel);
    }

}
