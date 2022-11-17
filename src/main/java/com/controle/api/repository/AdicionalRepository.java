package com.controle.api.repository;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.controle.api.model.Adicional;


@Repository
public interface AdicionalRepository  extends JpaRepository<Adicional,Long>{

	
	Page<Adicional> findByStatus(Boolean status, Pageable pageable);
	
}