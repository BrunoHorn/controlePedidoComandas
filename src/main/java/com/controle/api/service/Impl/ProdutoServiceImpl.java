package com.controle.api.service.Impl;

import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.controle.api.dto.ProdutoDto;
import com.controle.api.dto.ProdutoInputDto;
import com.controle.api.exception.EntidadeEmUsoException;
import com.controle.api.exception.EntidadeNaoEncontradaException;
import com.controle.api.mapper.ProdutoMapper;
import com.controle.api.model.Produto;
import com.controle.api.repository.ProdutoRepository;
import com.controle.api.service.ProdutoService;

@Service
public class ProdutoServiceImpl implements ProdutoService{
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@Autowired
	private ProdutoMapper produtoMapper;

	public ProdutoDto save(@Valid ProdutoInputDto produtoInputDto, Long id) {
		var produto = produtoMapper.toProduto(produtoInputDto); 
		
        if (Objects.nonNull(id)) {
        	produto.setId(id);
        }		
		produto = produtoRepository.save(produto);		
		ProdutoDto produtoDto = produtoMapper.toProdutoDto(produto);		
	return produtoDto;
	}

	public List<ProdutoDto> findAll() {		
		return produtoMapper.toProdutoListDto(produtoRepository.findAll());
	}

	public Produto findById(Long id) {
		var produtoOptional = produtoRepository.findById(id);
		if(produtoOptional.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não a produtos cadastrados com esse ID");
		}
		return produtoOptional.get();
	}

	public Page<ProdutoDto> buscaListaproduto(Boolean status, Pageable pageable) {
		if (status == null) {
			status= true;    		
		} 
		Page<Produto> page = produtoRepository.findByStatus(status, pageable);
		Page<ProdutoDto> produtoDto = page.map(pagedto -> produtoMapper.toProdutoDto(pagedto));
		return produtoDto;
	}

	public void excluir(Long id) {
		try { 	
			var produto = findById(id);
			produtoRepository.delete(produto);     
		} catch(DataIntegrityViolationException e){
		throw new EntidadeEmUsoException("Produto está em uso , só pode ser desativado");
		}
    }
	
	
	
	
}
