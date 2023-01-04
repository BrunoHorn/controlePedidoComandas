package com.controle.api.service;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controle.api.dto.ComandaDto;
import com.controle.api.dto.ComandaInputDto;
import com.controle.api.model.Comanda;

public interface ComandaService {
	
	public ComandaDto save(@Valid ComandaInputDto comandaInputDto, Long id);

	public Comanda findById(Long id);
	
	public Page<ComandaDto> buscaListaComanda(Boolean status, Pageable pageable);
	
	public void excluir(Long id);
}
