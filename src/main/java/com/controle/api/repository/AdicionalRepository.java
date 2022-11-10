package com.controle.api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.api.model.Adicional;

@Repository
public interface AdicionalRepository  extends JpaRepository<Adicional,Long>{

}