package com.myprojects.cadclients.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.myprojects.cadclients.model.ClientModel;

@Repository
public interface ClientRepository extends JpaRepository<ClientModel, Long> {

    Optional<ClientModel> findByCpf(String cpf);

    Optional<ClientModel> findByName(String name);

    List<ClientModel> findByNameContaining(String name);

    void deleteByNameContainingIgnoreCase(String name);
}
