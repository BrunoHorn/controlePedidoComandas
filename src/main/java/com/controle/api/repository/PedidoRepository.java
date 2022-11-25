package com.controle.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.controle.api.enumerado.StatusPedido;
import com.controle.api.model.Pedido;



@Repository
public interface PedidoRepository extends JpaRepository<Pedido, Long> {
	
	Page<Pedido> findByStatus(StatusPedido status, Pageable pageable);
			
	@Query(value = "select * from pedido p"
			+ " where p.status = 'REALIZADO' "
			+ " order by p.data_atualizacao  asc", nativeQuery = true)
	List<Pedido>findAllPreparoPedidos();
	

}
