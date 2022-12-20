package com.controle.api.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controle.api.dto.ProdutoDto;
import com.controle.api.dto.ProdutoInputDto;
import com.controle.api.model.Produto;

public interface ProdutoService {
	
	ProdutoDto save(@Valid ProdutoInputDto produtoInputDto, Long id);
	
	List<ProdutoDto> findAll();
	
	Produto findById(Long id);
	
	Page<ProdutoDto> buscaListaproduto(Boolean status, Pageable pageable);
	
	void excluir(Long id);

}
