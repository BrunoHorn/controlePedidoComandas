package com.controle.api.service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.controle.api.dto.PedidoCozinhaDto;
import com.controle.api.dto.PedidoDto;
import com.controle.api.dto.PedidoInputDto;
import com.controle.api.dto.PedidoretornoStatusDto;
import com.controle.api.dto.ProdutoretornoPedidoDto;
import com.controle.api.dto.PrudutoAdcPedidoDto;
import com.controle.api.enumerado.StatusPedido;
import com.controle.api.exception.EntidadeEmUsoException;
import com.controle.api.exception.EntidadeNaoEncontradaException;
import com.controle.api.mapper.PedidoMapper;
import com.controle.api.model.Comanda;
import com.controle.api.model.Pedido;
import com.controle.api.model.Produto;
import com.controle.api.repository.PedidoRepository;

@Service
public class PedidoService {

	@Autowired
	private PedidoMapper pedidoMapper;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	@Autowired
	private ProdutoService ProdutoService;
	
	@Autowired
	private ComandaService comandaService;
	 		
	public PedidoDto save(@Valid PedidoInputDto pedidoInputDto, Long id) {
		var pedido = pedidoMapper.toPedido(pedidoInputDto); 
		var produto = ProdutoService.findById(pedidoInputDto.getProdutoId());//arrumar        	
		var comanda = comandaService.findById(pedidoInputDto.getComandaId());//arrumar
						
		if (Objects.nonNull(id)) {
        	pedido.setId(id);
        }	
		     pedido.setDataAtualizacao(LocalDateTime.now());   
		     pedido.setComanda(comanda); 
		     pedido.setProduto(produto);
		     pedidoRepository.save(pedido);		
		     
		PedidoDto pedidoDto = montaRetornoPedidoDto(pedido,produto,comanda);		
	return pedidoDto;
	}
	
	public PedidoDto montaRetornoPedidoDto(Pedido pedido,Produto produto,Comanda comanda) {
		PedidoDto pedidoDto = new PedidoDto();
		PrudutoAdcPedidoDto prudutoAdcPedidoDto = new PrudutoAdcPedidoDto();
		pedidoDto.setId(pedido.getId());
		pedidoDto.setObservacao(pedido.getObservacao());
		pedidoDto.setStatus(pedido.getStatus());
		pedidoDto.setDataAtualizacao(pedido.getDataAtualizacao());
		pedidoDto.setComandaId(comanda.getId());
		prudutoAdcPedidoDto.setNome(produto.getNome());
		prudutoAdcPedidoDto.setObeservacao(produto.getObeservacao());
		prudutoAdcPedidoDto.setValor(produto.getValor());
		pedidoDto.setProduto(prudutoAdcPedidoDto);
		
		return pedidoDto;
	}

	public Pedido findById(Long id) {
		var pedidoOptional = pedidoRepository.findById(id);
		if (pedidoOptional.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não a pedidos cadastrados com esse ID");//arrumar
		}
		return pedidoOptional.get();
	}
	
     public  void alteraStatuspedido (Pedido pedido, StatusPedido statusPedido) {    	
     	pedido.setStatus(statusPedido);
     	pedidoRepository.save(pedido);
     }

     public List<PedidoretornoStatusDto> montaRetornoPedidoListDto(List<Pedido> pedido) {
    	 List<PedidoretornoStatusDto> pedidoListDto = new ArrayList<>();
    	 
    	 for (Pedido pd : pedido) {
    		 PedidoretornoStatusDto pedidoDto= new PedidoretornoStatusDto();
    		 ProdutoretornoPedidoDto produtoretornoPedidoDto = new ProdutoretornoPedidoDto();
    		 
    		 pedidoDto.setPedidoId(pd.getId());
    		 pedidoDto.setObservacao(pd.getObservacao());
    		 pedidoDto.setDataPedido(pd.getDataAtualizacao());
    		 pedidoDto.setComandaId(pd.getComanda().getId());    		 
    		 produtoretornoPedidoDto.setId(pd.getProduto().getId());
    		 produtoretornoPedidoDto.setNome(pd.getProduto().getNome());    		
    		 pedidoDto.setProduto(produtoretornoPedidoDto);    		 
    		 pedidoListDto.add(pedidoDto);
    	 }
    	 return pedidoListDto;
    	 
     }

	public Page<PedidoDto> buscaListaPedido(StatusPedido status, Pageable pageable) {
		Page<Pedido> page = pedidoRepository.findByStatus(status, pageable);
		Page<PedidoDto> pedidosDto = page.map(pagedto -> pedidoMapper.toPedidoDto(pagedto));
		return pedidosDto;
	}

	public void excluir(Long id) {
		try { 	
			var pedido = findById(id);
			pedidoRepository.delete(pedido);     
		} catch(DataIntegrityViolationException e){
			throw new EntidadeEmUsoException("Pedido está em uso , só pode ser desativado");
		}
    }

	public List<PedidoCozinhaDto> buscaPedidosCozinhao() {
		List<Pedido> pedidosLista= pedidoRepository.findAllPreparoPedidos();				
		List<PedidoCozinhaDto> pedidoCozinhaDtoList = new ArrayList<>();
		for (Pedido pedido : pedidosLista ) {						
			pedidoCozinhaDtoList.add(pedidoMapper.toPedidoCozinhaDto(pedido, pedido.getProduto()));
		}		
		return pedidoCozinhaDtoList;
	}    
}
