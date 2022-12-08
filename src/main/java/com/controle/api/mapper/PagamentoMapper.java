package com.controle.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.controle.api.dto.PagamentoAdicionalDto;
import com.controle.api.dto.PagamentoProdutoDto;
import com.controle.api.model.AdicionalPedido;
import com.controle.api.model.Pedido;
import com.controle.api.model.Produto;
import com.controle.api.repository.AdicionalPedidoRepository;

@Component
public class PagamentoMapper {
	
	@Autowired
	AdicionalPedidoRepository adicionalPedidoRepository;
	
	
	public PagamentoProdutoDto toPagamentoProdutoDto(Pedido pedido , Produto produto ) {
		var pagamentoProdutoDto = new PagamentoProdutoDto();
				
		pagamentoProdutoDto.setNomeProduto(pedido.getProduto().getNome());
		pagamentoProdutoDto.setValorProduto(pedido.getProduto().getValor());
						
		List<AdicionalPedido> adicionaisProdutoList = adicionalPedidoRepository.findByPedidoAndProduto(pedido, produto);
				
		List<PagamentoAdicionalDto> pagamentoAdicionalListDto = new ArrayList<>();
			for(AdicionalPedido adicionalPedido :adicionaisProdutoList ) {		
				PagamentoAdicionalDto pagamentoAdicionalDto = new PagamentoAdicionalDto();
				pagamentoAdicionalDto.setNomeAdicional(adicionalPedido.getAdicional().getNome());
				pagamentoAdicionalDto.setValorAdicional(adicionalPedido.getAdicional().getValor());
				pagamentoAdicionalListDto.add(pagamentoAdicionalDto);			
				}
		pagamentoProdutoDto.setPagamentoAdicionalDto(pagamentoAdicionalListDto);
		pagamentoProdutoDto.setStatusPedido(pedido.getStatus());
		
		return pagamentoProdutoDto;
	}
	

}
