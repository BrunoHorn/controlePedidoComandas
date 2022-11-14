package com.controle.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.api.dto.ProdutoDto;
import com.controle.api.dto.ProdutoInputDto;
import com.controle.api.mapper.ProdutoMapper;
import com.controle.api.model.Produto;
import com.controle.api.repository.ProdutoRepository;

@Service
public class ProdutoService {
	
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
			throw new RuntimeException("Não a produtos cadastrados com esse ID");
		}
		return produtoOptional.get();
	}

	public List<Produto> buscaListaProduto(Boolean status) {
   	 List <Produto> produtos = new ArrayList<>();	
   	 if (status == null ) {
   		 status= true;
   		produtos = produtoRepository.buscaListaPdt(status);
   	 } else  {
   		produtos = produtoRepository.buscaListaPdt(status);
   	 } 	
   	return produtos;
   }
	
	
	
	
}
