package com.controle.api.mapper;

import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.controle.api.dto.ProdutoDto;
import com.controle.api.dto.ProdutoInputDto;
import com.controle.api.model.Produto;


@Component
public class ProdutoMapper {
	
	public Produto toProduto(ProdutoInputDto produtoInputDto) {
		var produto = new Produto();
		 BeanUtils.copyProperties(produtoInputDto, produto); 
		 return produto;
	}
	
	public ProdutoDto toProdutoDto(Produto produto) {
		var produtoDto = new ProdutoDto();
		 BeanUtils.copyProperties(produto, produtoDto); 
		 return produtoDto;
	}

	public List<ProdutoDto> toProdutoListDto(List<Produto> produto) {
		List<ProdutoDto> produtoListDto = new ArrayList<>();		
		for (Produto pr : produto) {
			var produtoDto = new ProdutoDto();
			produtoDto.setId(pr.getId());
			produtoDto.setNome(pr.getNome());
			produtoDto.setObeservacao(pr.getObeservacao());
		//	produtoDto.setTipo(pr.getTipo());
			produtoDto.setValor(pr.getValor());
			produtoDto.setStatus(pr.getStatus());
			
			produtoListDto.add(produtoDto);
		}				 
		 return produtoListDto;
	}
	
	
}
