package com.controle.api.service;

import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.api.dto.AdicionalPedidoDto;
import com.controle.api.dto.AdicionalPedidoInputDto;
import com.controle.api.mapper.AdicionalPedidoMapper;
import com.controle.api.model.AdicionalPedido;
import com.controle.api.repositorie.AdicionalPedidoRepository;

@Service
public class AdicionalPedidoService {

	@Autowired
	private AdicionalPedidoRepository AdcPedidoRepository;
	
	@Autowired
	private AdicionalPedidoMapper AdcPedidoMapper;
	
	@Autowired
	private ProdutoService  produtoService;
	
	@Autowired
	private AdicionalService adicionalService;
	
	public AdicionalPedidoDto save(@Valid AdicionalPedidoInputDto AdcPedidoInputDto, Long id) {
		var adicionalPedido = new AdicionalPedido();
		var produto = produtoService.findById(AdcPedidoInputDto.getProdutoId());
	//	var pedido = new Pedido();
		List<Long> ids= AdcPedidoInputDto.getAdicionalId();
		for (Long adcId :ids){
			var adicional =adicionalService.findById(adcId);
			adicionalPedido.setAdicional(adicional);
			adicionalPedido.setProduto(produto);
			adicionalPedido.setPedido(AdcPedidoInputDto.getPedido());
			adicionalPedido.setObservacao(AdcPedidoInputDto.getObservacao());
			adicionalPedido = AdcPedidoRepository.save(adicionalPedido);
		}
	        		       		
		AdicionalPedidoDto adcPedidoDto = AdcPedidoMapper.toAdcpedidoDto(adicionalPedido);		
	return adcPedidoDto;
	}
	

}




