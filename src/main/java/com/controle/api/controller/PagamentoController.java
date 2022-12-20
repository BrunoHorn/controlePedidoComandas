package com.controle.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.controle.api.dto.PagamentoComandaDto;
import com.controle.api.model.Pedido;
import com.controle.api.service.Impl.PagamentoServiceImpl;

import io.swagger.annotations.Api;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/Pagamento")
@Api(value="API controle de Pagamento")
public class PagamentoController {
		
	@Autowired
	PagamentoServiceImpl pagamentoServiceImpl;
	
	@GetMapping("/{id}")	    
	public ResponseEntity<PagamentoComandaDto> getPedidosPagamento(Long id) {		
		 List<Pedido> pedidosLista = pagamentoServiceImpl.buscaComandaParaPagamento(id);
		 PagamentoComandaDto pagamentoComandaDto = new PagamentoComandaDto();
		 pagamentoComandaDto = pagamentoServiceImpl.montaRetornoPagamentoComandaDto(pedidosLista, id);
        return ResponseEntity.status(HttpStatus.OK).body(pagamentoComandaDto);
	}
	
}
