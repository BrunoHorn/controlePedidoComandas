package com.controle.api.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.controle.api.dto.PedidoDto;
import com.controle.api.dto.PedidoInputDto;
import com.controle.api.dto.PrudutoAdcPedidoDto;
import com.controle.api.model.Pedido;

@Component
public class PedidoMapper {
	
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


}
