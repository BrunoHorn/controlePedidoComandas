package com.controle.api.service;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.api.dto.AdcPedidoRetornoDto;
import com.controle.api.dto.AdicionalPedidoInputDto;
import com.controle.api.dto.AdicionalPedidoRetornoDto;
import com.controle.api.dto.PrudutoAdcPedidoDto;
import com.controle.api.model.AdicionalPedido;
import com.controle.api.model.Pedido;
import com.controle.api.model.Produto;
import com.controle.api.repositorie.AdicionalPedidoRepository;
import com.controle.api.repositorie.PedidoRepository;

@Service
public class AdicionalPedidoService {

	@Autowired
	private AdicionalPedidoRepository AdcPedidoRepository;
		
	@Autowired
	private ProdutoService  produtoService;
	
	@Autowired
	private AdicionalService adicionalService;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	public AdicionalPedidoRetornoDto save(@Valid AdicionalPedidoInputDto AdcPedidoInputDto, Long id) {		
		var produto = produtoService.findById(AdcPedidoInputDto.getProdutoId());
     	var pedido = pedidoRepository.findById(AdcPedidoInputDto.getPedidoId());        	
     	List<Long> ids= AdcPedidoInputDto.getAdicionalId();
		for (Long adcId :ids){
			var adicionalPedido = new AdicionalPedido();
			var adicional =adicionalService.findById(adcId);
			adicionalPedido.setAdicional(adicional);
			adicionalPedido.setProduto(produto);
			adicionalPedido.setPedido(pedido.get());
			adicionalPedido.setObservacao(AdcPedidoInputDto.getObservacao());
			
			 AdcPedidoRepository.save(adicionalPedido);
		}
		var adcPedidoRetornoDto =new AdicionalPedidoRetornoDto() ; 		       		
				adcPedidoRetornoDto  = MontaDtoadcPedidoRetorno(ids,produto,pedido.get());	
	return adcPedidoRetornoDto;
	}
	
	private AdicionalPedidoRetornoDto MontaDtoadcPedidoRetorno(List<Long> adcPedidoInputDto,Produto produto,Pedido pedido) {
		var adcPedidoRetornoDto =new AdicionalPedidoRetornoDto();
		var prudutoAdcPedidoDto =  new PrudutoAdcPedidoDto();
		adcPedidoRetornoDto.setPedidoId(pedido.getId());
		
		prudutoAdcPedidoDto.setNomeProduto(produto.getNome());
		prudutoAdcPedidoDto.setValorProduto(produto.getValor());
		prudutoAdcPedidoDto.setObeservacaoProduto(produto.getObeservacao());  
		adcPedidoRetornoDto.setProduto(prudutoAdcPedidoDto);
		List<AdcPedidoRetornoDto> adcPedidoRetornoListDto = new ArrayList<>();	
		List<Long> ids= adcPedidoInputDto;
		for (Long adcId :ids) {
			var adcRetornoDto = new AdcPedidoRetornoDto();
			var adicional =adicionalService.findById(adcId);
			adcRetornoDto.setNomeAdicional(adicional.getNome());
			adcRetornoDto.setPreçoadicional(adicional.getValor());			
			adcPedidoRetornoListDto.add(adcRetornoDto);
		}
		adcPedidoRetornoDto.setAdicional(adcPedidoRetornoListDto);
		return adcPedidoRetornoDto;
	}

}




