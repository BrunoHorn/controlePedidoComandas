package com.controle.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.api.dto.PedidoDto;
import com.controle.api.dto.PedidoInputDto;
import com.controle.api.dto.ProdutoDto;
import com.controle.api.dto.PrudutoAdcPedidoDto;
import com.controle.api.enumerado.StatusPedido;
import com.controle.api.mapper.PedidoMapper;
import com.controle.api.model.Comanda;
import com.controle.api.model.Pedido;
import com.controle.api.model.Produto;
import com.controle.api.repository.ComandaRepository;
import com.controle.api.repository.PedidoRepository;
import com.controle.api.repository.ProdutoRepository;



@Service
public class PedidoService {

	@Autowired
	private PedidoMapper pedidoMapper;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoRepository produtoRepository ;
	
	@Autowired
	private ComandaRepository comandaRepository;
	
	public PedidoDto save(@Valid PedidoInputDto pedidoInputDto, Long id) {
		var pedido = pedidoMapper.toPedido(pedidoInputDto); 
		var produto = produtoRepository.findById(pedidoInputDto.getProduto());        	
		var comanda = comandaRepository.findById(pedidoInputDto.getComanda());
		if (Objects.nonNull(id)) {
        	pedido.setId(id);
        }	
		     pedido.setDataAtualizacao(LocalDateTime.now());   
		     pedido.setComanda(comanda.get()); 
		     pedido.setProduto(produto.get());
		     pedidoRepository.save(pedido);		
		     
		PedidoDto pedidoDto = montaRetornoPedidoDto(pedido,produto.get(),comanda.get());		
	return pedidoDto;
	}
	
	public PedidoDto montaRetornoPedidoDto(Pedido pedido,Produto produto,Comanda comanda) {
		PedidoDto pedidoDto = new PedidoDto();
		PrudutoAdcPedidoDto prudutoAdcPedidoDto = new PrudutoAdcPedidoDto();
		pedidoDto.setId(pedido.getId());
		pedidoDto.setObservacao(pedido.getObservacao());
		pedidoDto.setStatus(pedido.getStatus());
		pedidoDto.setDataAtualizacao(pedido.getDataAtualizacao());
		pedidoDto.setComanda(comanda.getId());
		prudutoAdcPedidoDto.setNomeProduto(produto.getNome());
		prudutoAdcPedidoDto.setObeservacaoProduto(produto.getObeservacao());
		prudutoAdcPedidoDto.setValorProduto(produto.getValor());
		pedidoDto.setProduto(prudutoAdcPedidoDto);
		
		return pedidoDto;
	}

	public Pedido findById(Long id) {
		var pedidoOptional =pedidoRepository.findById(id);
		if (pedidoOptional.isEmpty()) {
			throw new RuntimeException("Não a pedidos cadastrados com esse ID");
		}
		return pedidoOptional.get();
	}
	
     public  void alteraStatuspedido (Pedido pedido, StatusPedido statusPedido) {    	
     	pedido.setStatus(statusPedido);
     	pedidoRepository.save(pedido);
     }
	
     public List<Pedido> buscaListaStatusPedido(StatusPedido statusPedido){
    	 List <Pedido> pedidos = new ArrayList<>();	
    	 if (statusPedido != null){
    			pedidos= pedidoRepository.buscaListaStatusPedido(statusPedido.toString());
    		} else {
    			pedidos =pedidoRepository.buscaListaStatusPedido("TODOS");
    			}
    	 return pedidos;
     }
	


}
