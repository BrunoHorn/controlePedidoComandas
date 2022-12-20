package com.controle.api.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controle.api.dto.AdicionalDto;
import com.controle.api.dto.AdicionalInputDto;
import com.controle.api.model.Adicional;

public interface AdicionalService {

	Page<AdicionalDto> buscaListaAdicional(Boolean status, Pageable pageable);
	
	Adicional findById(Long id);
	
	void excluir(Long id);
	
	Page<AdicionalDto> findAll(Pageable pageable);
	
	AdicionalDto save(@Valid AdicionalInputDto AdicionalInputDto, Long id);
		
}
