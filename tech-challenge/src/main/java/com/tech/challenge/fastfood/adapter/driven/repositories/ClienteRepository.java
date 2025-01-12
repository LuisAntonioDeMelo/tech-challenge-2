package com.tech.challenge.fastfood.adapter.driven.repositories;

import com.tech.challenge.fastfood.adapter.driven.persistence.ClienteEntity;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface ClienteRepository extends JpaRepository<ClienteEntity,Long> {
    Optional<ClienteEntity> findByCpf(String cpf);
}
