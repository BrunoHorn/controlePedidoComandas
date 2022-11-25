package com.controle.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.api.model.AdicionalPedido;
import com.controle.api.model.Pedido;
import com.controle.api.model.Produto;

@Repository
public interface AdicionalPedidoRepository extends JpaRepository<AdicionalPedido, Long> {

	List<AdicionalPedido> findByPedidoAndProduto(Pedido pedido, Produto produto);

	
}
