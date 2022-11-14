package com.controle.api.service;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.api.dto.AdcPedidoRetornoDto;
import com.controle.api.dto.AdicionalPedidoInputDto;
import com.controle.api.dto.AdicionalPedidoRetornoDto;
import com.controle.api.model.AdicionalPedido;
import com.controle.api.model.Pedido;
import com.controle.api.model.Produto;
import com.controle.api.repository.AdicionalPedidoRepository;
import com.controle.api.repository.PedidoRepository;

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
	
	
	
	public List<AdicionalPedidoRetornoDto> save(@Valid AdicionalPedidoInputDto AdcPedidoInputDto, Long id) {		
		var produto = produtoService.findById(AdcPedidoInputDto.getProdutoId());
     	var pedido = pedidoRepository.findById(AdcPedidoInputDto.getPedidoId());        	
     	
		if(!produto.getStatus()) {
			throw new RuntimeException("Produto solicitadado está indisponivel!");
		}
     	    	
     	List<AdicionalPedido> adicionalPedidoList = new ArrayList<>();	
     	List<Long> ids= AdcPedidoInputDto.getAdicionalId();
		for (Long adcId :ids){
			var adicionalPedido = new AdicionalPedido();
			var adicional =adicionalService.findById(adcId);
			if(!adicional.getStatus()) {
				throw new RuntimeException("Adicional :"+ adicional.getNome()+ "  está indisponivel!");
			}			
			adicionalPedido.setAdicional(adicional);
			adicionalPedido.setProduto(produto);
			adicionalPedido.setPedido(pedido.get());
			adicionalPedido.setObservacao(AdcPedidoInputDto.getObservacao());
			
			adicionalPedidoList.add(AdcPedidoRepository.save(adicionalPedido));
		}
		List<AdicionalPedidoRetornoDto> adcPedidoRetornoDto =new ArrayList<>(); 		       		
				
				adcPedidoRetornoDto  = MontaDtoadcPedidoRetorno(adicionalPedidoList,produto,pedido.get());	
	return adcPedidoRetornoDto;
	}
	
	private List<AdicionalPedidoRetornoDto> MontaDtoadcPedidoRetorno(List<AdicionalPedido> adicionalPedidoList,Produto produto,Pedido pedido) {
		List<AdicionalPedidoRetornoDto> AdicionalPedidoRetornoListDto= new ArrayList<>();	
		
		for(AdicionalPedido adicionalPedido:adicionalPedidoList ) {
			var adicionalPedidoRetornoDto =new AdicionalPedidoRetornoDto();				
			adicionalPedidoRetornoDto.setId(adicionalPedido.getId());
			 
			var AdcPedidoRetornoDto = new AdcPedidoRetornoDto();			
			AdcPedidoRetornoDto.setNome(adicionalPedido.getAdicional().getNome());
			adicionalPedidoRetornoDto.setAdicional(AdcPedidoRetornoDto);			 
			adicionalPedidoRetornoDto.setProdutoId(produto.getId());
		
			AdicionalPedidoRetornoListDto.add(adicionalPedidoRetornoDto);
		}
		
		return AdicionalPedidoRetornoListDto;
	}

}




