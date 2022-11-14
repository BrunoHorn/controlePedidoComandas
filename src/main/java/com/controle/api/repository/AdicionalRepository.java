package com.controle.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.controle.api.model.Adicional;


@Repository
public interface AdicionalRepository  extends JpaRepository<Adicional,Long>{

	@Query(value ="select *from adicional where status_adicional= :status ", nativeQuery = true)
	List<Adicional> buscaListaAdc(Boolean status);
	
}
