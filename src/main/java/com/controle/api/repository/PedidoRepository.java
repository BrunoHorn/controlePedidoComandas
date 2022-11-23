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
	
	@Query(value = "select p.id ,c.id as idComanda ,p2.nome ,p2.observacao ,p.data_atualizacao , a.nome as nome_adicional from pedido p "
			+ "inner join produto p2  on (p.produto_id = p2.id) "
			+ "inner join  comanda c on (p.comanda_id = c.id) "
			+ "inner join adicional_pedido ap  on (ap.pedido_id = p.id)  "
			+ "inner join adicional a on (ap.adicional_id = a.id)  "
			+ "where p.status = 'REALIZADO' "
			+ "order by p.data_atualizacao  asc ", nativeQuery = true)
	List<Object> findAllPreparoPedidos();
	
}
