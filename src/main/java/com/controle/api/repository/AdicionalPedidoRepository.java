package com.controle.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.api.model.AdicionalPedido;

@Repository
public interface AdicionalPedidoRepository extends JpaRepository<AdicionalPedido, Long> {

}
