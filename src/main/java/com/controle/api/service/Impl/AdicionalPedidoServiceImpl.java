package com.controle.api.service.Impl;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.stereotype.Service;

import com.controle.api.dto.AdcPedidoRetornoDto;
import com.controle.api.dto.AdicionalPedidoInputDto;
import com.controle.api.dto.AdicionalPedidoRetornoDto;
import com.controle.api.exception.EntidadeEmUsoException;
import com.controle.api.exception.EntidadeNaoEncontradaException;
import com.controle.api.exception.NegocioException;
import com.controle.api.model.AdicionalPedido;
import com.controle.api.model.Pedido;
import com.controle.api.model.Produto;
import com.controle.api.repository.AdicionalPedidoRepository;
import com.controle.api.repository.PedidoRepository;

@Service
public class AdicionalPedidoServiceImpl {

	@Autowired
	private AdicionalPedidoRepository adicionalPedidoRepository;
		
	@Autowired
	private ProdutoServiceImpl  produtoServiceImpl;
	
	@Autowired
	private AdicionalServiceImpl adicionalServiceImpl;
	
	@Autowired
	private PedidoRepository pedidoRepository;

	public List<AdicionalPedidoRetornoDto> save(@Valid AdicionalPedidoInputDto AdcPedidoInputDto, Long id) throws Exception {		
		var produto = produtoServiceImpl.findById(AdcPedidoInputDto.getProdutoId());
     	
		var pedido = pedidoRepository.findById(AdcPedidoInputDto.getPedidoId());        	
     	
		if(!produto.getStatus()) {
			throw new EntidadeNaoEncontradaException("Produto solicitadado está indisponivel!");
		}
     	    	
     	List<AdicionalPedido> adicionalPedidoList = new ArrayList<>();	
     	List<Long> ids= AdcPedidoInputDto.getAdicionalId();
		for (Long adcId :ids){
			var adicionalPedido = new AdicionalPedido();
			var adicional =adicionalServiceImpl.findById(adcId);
			if(!adicional.getStatus()) {
				throw new NegocioException("Adicional :"+ adicional.getNome()+ "  está indisponivel!");
			}			
			adicionalPedido.setAdicional(adicional);
			adicionalPedido.setProduto(produto);
			adicionalPedido.setPedido(pedido.get());
			adicionalPedido.setObservacao(AdcPedidoInputDto.getObservacao());
			
			adicionalPedidoList.add(adicionalPedidoRepository.save(adicionalPedido));
		}
		List<AdicionalPedidoRetornoDto> adcPedidoRetornoDto =new ArrayList<>(); 		       						
		adcPedidoRetornoDto  = MontaDtoadcPedidoRetorno(adicionalPedidoList,produto,pedido.get());	
		
		return adcPedidoRetornoDto;
	}
	
	private List<AdicionalPedidoRetornoDto> MontaDtoadcPedidoRetorno(List<AdicionalPedido> adicionalPedidoList,Produto produto,Pedido pedido) {
		List<AdicionalPedidoRetornoDto> AdicionalPedidoRetornoListDto= new ArrayList<>();	
		
		for(AdicionalPedido adicionalPedido:adicionalPedidoList ) {
			var adicionalPedidoRetornoDto = new AdicionalPedidoRetornoDto();				
			adicionalPedidoRetornoDto.setId(adicionalPedido.getId());
			 
			var AdcPedidoRetornoDto = new AdcPedidoRetornoDto();			
			AdcPedidoRetornoDto.setNome(adicionalPedido.getAdicional().getNome());
			adicionalPedidoRetornoDto.setAdicional(AdcPedidoRetornoDto);			 
			adicionalPedidoRetornoDto.setProdutoId(produto.getId());
		
			AdicionalPedidoRetornoListDto.add(adicionalPedidoRetornoDto);
		}		
		return AdicionalPedidoRetornoListDto;
	}

	public AdicionalPedido findById(Long id) {
		var adicionalPedidoOptional = adicionalPedidoRepository.findById(id);
		if(adicionalPedidoOptional.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não há adicionaisPedidos cadastrados com esse ID");
		}
		return adicionalPedidoOptional.get();
	}

	public void excluir(Long id) {
		try { 	
			var adicionalPedido = findById(id);
			adicionalPedidoRepository.delete(adicionalPedido);     
		} catch(DataIntegrityViolationException e){
			throw new EntidadeEmUsoException("AdicionalPedido está em uso , só pode ser desativado");
		}
    }
		
}




