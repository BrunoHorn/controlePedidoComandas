package com.controle.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controle.api.dto.ComandaDto;
import com.controle.api.dto.PedidoDto;
import com.controle.api.dto.PedidoInputDto;
import com.controle.api.dto.ProdutoDto;
import com.controle.api.dto.ProdutoInputDto;
import com.controle.api.enumerado.StatusPedido;
import com.controle.api.mapper.PedidoMapper;
import com.controle.api.model.Comanda;
import com.controle.api.model.Pedido;
import com.controle.api.model.Produto;
import com.controle.api.repository.PedidoRepository;
import com.controle.api.service.PedidoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoMapper pedidoMapper;
	
	@Autowired
	private PedidoRepository pedidoRepository;
	
	
	@PostMapping
	@ApiOperation(value="Cadastrar novo pedido")
    public ResponseEntity<PedidoDto> savePedido(@RequestBody @Valid PedidoInputDto pedidoInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoService.save(pedidoInputDto, null));
    }
	
	
    @GetMapping("/{id}")
    @ApiOperation(value="Busca produto cadastrado pelo ID")
    public ResponseEntity<PedidoDto> getPedidoId(@PathVariable(value = "id") Long id){
    	var pedido =pedidoService.findById(id);   	
        return ResponseEntity.status(HttpStatus.OK).body(pedidoMapper.toPedidoDto(pedido));
    }
	

    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza pedido status pelo ID")
	public ResponseEntity<PedidoDto> alteraStatusPedido(@PathVariable(value = "id")Long id,StatusPedido statusPedido){
    	Pedido pedidoUpdate = pedidoService.findById(id);
    	var produto = pedidoUpdate.getProduto();
    	var comanda =pedidoUpdate.getComanda();
    	pedidoService.alteraStatuspedido(pedidoUpdate, statusPedido);
    	
    	var pedidoDto = pedidoService.montaRetornoPedidoDto(pedidoUpdate, produto, comanda);
    	
		return ResponseEntity.status(HttpStatus.OK).body(pedidoDto);
	}
	
	@GetMapping	    
	public ResponseEntity<List<Pedido>> getStatusDePedidos(@RequestParam(required = false) StatusPedido statusPedido){
		List <Pedido> pedidos = new ArrayList<>();
		pedidoService.buscaListaStatusPedido(statusPedido);
	
		return ResponseEntity.status(HttpStatus.OK).body(pedidos); 
	}

}
