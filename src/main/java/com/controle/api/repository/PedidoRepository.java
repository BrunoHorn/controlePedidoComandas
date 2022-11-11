package com.controle.api.repository;

import java.util.List;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.controle.api.model.Pedido;

@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	@Query(value ="select * from pedido p where status_pedido = :statusPedido or status_pedido = 'TODOS' ", nativeQuery = true)
	List<Pedido> buscaListaStatusPedido(String statusPedido);
	

}
