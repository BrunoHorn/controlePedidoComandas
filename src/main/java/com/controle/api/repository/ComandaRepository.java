package com.controle.api.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.api.model.Comanda;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {

	Page<Comanda> findByStatus(Boolean status, Pageable pageable);
}
