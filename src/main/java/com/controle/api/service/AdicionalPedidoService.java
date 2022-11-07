package com.controle.api.service;

import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.api.dto.AdicionalPedidoDto;
import com.controle.api.dto.AdicionalPedidoInputDto;
import com.controle.api.mapper.AdicionalPedidoMapper;
import com.controle.api.repositorie.AdicionalPedidoRepository;

@Service
public class AdicionalPedidoService {

	@Autowired
	private AdicionalPedidoRepository AdcPedidoRepository;
	
	@Autowired
	private AdicionalPedidoMapper AdcPedidoMapper;
	
	public AdicionalPedidoDto save(@Valid AdicionalPedidoInputDto AdcPedidoInputDto, Long id) {
		var adicionalPedido = AdcPedidoMapper.toAdcPedido(AdcPedidoInputDto); 
		
        if (Objects.nonNull(id)) {
        	adicionalPedido.setId(id);
        }		
        adicionalPedido = AdcPedidoRepository.save(adicionalPedido);		
		AdicionalPedidoDto adcPedidoDto = AdcPedidoMapper.toAdcpedidoDto(adicionalPedido);		
	return adcPedidoDto;
	}
	

}
