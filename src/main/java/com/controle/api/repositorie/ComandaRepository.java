package com.controle.api.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.api.model.Comanda;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {

}
