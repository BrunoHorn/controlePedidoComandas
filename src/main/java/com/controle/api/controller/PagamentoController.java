package com.controle.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controle.api.enumerado.StatusPedido;
import com.controle.api.model.Pedido;
import com.controle.api.repository.PedidoRepository;
import com.controle.api.service.ComandaService;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Pagamento")
@Api(value="API controle de Pagamento")
public class PagamentoController {
	
	@Autowired
	ComandaService comandaService;
	
	@Autowired
	PedidoRepository pedidoRepository;
	
	@GetMapping("/{id}")	    
	public ResponseEntity<List<Pedido>> getPedidosPagamento(Long id) {
		var comanda = comandaService.findById(id);
		 List<Pedido> pedidosLista = pedidoRepository.findAllPedidoByStatusAndComandaId(StatusPedido.ATENDIDO, comanda.getId());

        return ResponseEntity.status(HttpStatus.OK).body(pedidosLista);
	}
	
}
