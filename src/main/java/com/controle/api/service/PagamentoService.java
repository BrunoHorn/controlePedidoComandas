package com.controle.api.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.controle.api.dto.PagamentoComandaDto;
import com.controle.api.dto.PagamentoProdutoDto;
import com.controle.api.enumerado.StatusPedido;
import com.controle.api.mapper.PagamentoMapper;
import com.controle.api.model.Pedido;
import com.controle.api.repository.PedidoRepository;

@Service
public class PagamentoService {

	@Autowired
	ComandaService comandaService;
	@Autowired
	PagamentoMapper pagamentoMapper;
	
	@Autowired
	PedidoRepository pedidoRepository;
	public List<Pedido> buscaComandaParaPagamento(Long id) {
		var comanda = comandaService.findById(id);
		 List<Pedido> pedidosLista = pedidoRepository.findAllPedidoByStatusAndComandaId(StatusPedido.ATENDIDO, comanda.getId());
		
		 		 
		 return pedidosLista;
	}

	public PagamentoComandaDto montaRetornoPagamentoComandaDto(List<Pedido> pedidos,Long id) {
		var comanda = comandaService.findById(id);
		PagamentoComandaDto pagamentoComandaDto = new PagamentoComandaDto();
		List<PagamentoProdutoDto>pagamentoProdutoDto = new ArrayList<>();
		pagamentoComandaDto.setComandaId(comanda.getId());
		for (Pedido pedido : pedidos ) {												
			pagamentoProdutoDto.add(pagamentoMapper.toPagamentoProdutoDto(pedido, pedido.getProduto()));
		}	
		pagamentoComandaDto.setProdutoPagamentoDto(pagamentoProdutoDto);
		return pagamentoComandaDto;
	}
	
}
