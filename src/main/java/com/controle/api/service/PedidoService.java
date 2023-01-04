package com.controle.api.service;

import java.util.List;

import javax.validation.Valid;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.controle.api.dto.PedidoCozinhaDto;
import com.controle.api.dto.PedidoDto;
import com.controle.api.dto.PedidoInputDto;
import com.controle.api.enumerado.StatusPedido;
import com.controle.api.model.Comanda;
import com.controle.api.model.Pedido;
import com.controle.api.model.Produto;

public interface PedidoService {
	
	public PedidoDto save(@Valid PedidoInputDto pedidoInputDto, Long id);
	
	public PedidoDto montaRetornoPedidoDto(Pedido pedido,Produto produto,Comanda comanda);
	
	public Pedido findById(Long id);
	
	public Page<PedidoDto> buscaListaPedido(StatusPedido status, Pageable pageable);
	
	public  void alteraStatuspedido (Pedido pedido, StatusPedido statusPedido) ;

	public void excluir(Long id);
	
	public List<PedidoCozinhaDto> buscaPedidosCozinhao() ;
}
