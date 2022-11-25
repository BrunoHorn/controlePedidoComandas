package com.controle.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.controle.api.dto.PedidoCozinhaDto;
import com.controle.api.dto.PedidoDto;
import com.controle.api.dto.PedidoInputDto;
import com.controle.api.dto.PrudutoAdcPedidoDto;
import com.controle.api.model.AdicionalPedido;
import com.controle.api.model.Pedido;
import com.controle.api.model.Produto;
import com.controle.api.repository.AdicionalPedidoRepository;


@Component
public class PedidoMapper {
	
	@Autowired
	private AdicionalPedidoRepository adicionalPedidoRepository;
	
	public Pedido toPedido(PedidoInputDto pedidoInputDto) {
		var pedido = new Pedido();
		 BeanUtils.copyProperties(pedidoInputDto, pedido); 
		 return pedido;
	}
	
	public PedidoDto toPedidoDto(Pedido pedido) {
		var pedidoDto = new PedidoDto();
		var prudutoAdcPedidoDto= new PrudutoAdcPedidoDto();
		pedidoDto.setComandaId(pedido.getComanda().getId());
		pedidoDto.setDataAtualizacao(pedido.getDataAtualizacao());
		pedidoDto.setId(pedido.getId());
		pedidoDto.setObservacao(pedido.getObservacao());
		pedidoDto.setStatus(pedido.getStatus());
		prudutoAdcPedidoDto.setNome(pedido.getProduto().getNome());
		prudutoAdcPedidoDto.setObeservacao(pedido.getProduto().getObeservacao());
		prudutoAdcPedidoDto.setValor(pedido.getProduto().getValor());
		pedidoDto.setProduto(prudutoAdcPedidoDto);
		
		return pedidoDto;
	}
	
	
	public PedidoCozinhaDto toPedidoCozinhaDto(Pedido pedido , Produto produto ) {
		var pedidoCozinhaDto = new PedidoCozinhaDto();
		pedidoCozinhaDto.setComandaId(pedido.getComanda().getId());
		pedidoCozinhaDto.setNomeProduto(pedido.getProduto().getNome());
		pedidoCozinhaDto.setDataPedido(pedido.getDataAtualizacao());
		pedidoCozinhaDto.setObservacaoProduto(pedido.getProduto().getObeservacao());
				
		List<AdicionalPedido> adicionaisProdutoList = adicionalPedidoRepository.findByPedidoAndProduto(pedido, produto);
				
		List<String> adicionalPedidoList = new ArrayList<>();
			for(AdicionalPedido adicionalPedido :adicionaisProdutoList ) {		
				adicionalPedidoList.add(adicionalPedido.getAdicional().getNome());			
				}
		pedidoCozinhaDto.setNomeAdicional(adicionalPedidoList);	
		
		return pedidoCozinhaDto;
	}
	
	
	


	
	
}
