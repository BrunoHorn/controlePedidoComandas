package com.controle.api.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;
import com.controle.api.dto.PedidoDto;
import com.controle.api.dto.PedidoInputDto;
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
		 BeanUtils.copyProperties(pedido, pedidoDto); 
		 return pedidoDto;
	}


}
