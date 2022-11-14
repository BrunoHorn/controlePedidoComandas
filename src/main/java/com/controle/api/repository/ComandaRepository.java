package com.controle.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.controle.api.model.Comanda;

@Repository
public interface ComandaRepository extends JpaRepository<Comanda, Long> {

	@Query(value ="select * from comanda where status_comanda = :status ", nativeQuery = true)
	List<Comanda> buscaListaCmd(Boolean status);
	
}
