package com.controle.api.mapper;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.controle.api.dto.AdicionalPedidoDto;
import com.controle.api.dto.AdicionalPedidoInputDto;
import com.controle.api.model.AdicionalPedido;

@Component
public class AdicionalPedidoMapper {

	public AdicionalPedido toAdcPedido(AdicionalPedidoInputDto adcpedidoDto) {
		var adcPedido = new AdicionalPedido();
		 BeanUtils.copyProperties(adcpedidoDto, adcPedido); 
		 return adcPedido;
	}
	
	public AdicionalPedidoDto toAdcpedidoDto(AdicionalPedido adcPedido) {
		var adcPedidoDto = new AdicionalPedidoDto();
		 BeanUtils.copyProperties(adcPedido, adcPedidoDto); 
		 return adcPedidoDto;
	}
	
	
}
