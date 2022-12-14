package com.controle.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.controle.api.dto.PedidoCozinhaDto;
import com.controle.api.dto.PedidoDto;
import com.controle.api.dto.PedidoInputDto;
import com.controle.api.enumerado.StatusPedido;
import com.controle.api.mapper.PedidoMapper;
import com.controle.api.model.Pedido;
import com.controle.api.service.Impl.PedidoServiceImpl;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoServiceImpl pedidoServiceImpl;
	
	@Autowired
	private PedidoMapper pedidoMapper;
		
	@PostMapping
	@ApiOperation(value="Cadastrar novo pedido")
    public ResponseEntity<PedidoDto> savePedido(@RequestBody @Valid PedidoInputDto pedidoInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(pedidoServiceImpl.save(pedidoInputDto, null));
    }
	
	
    @GetMapping("/{id}")
    @ApiOperation(value="Busca produto cadastrado pelo ID")
    public ResponseEntity<PedidoDto> getPedidoId(@PathVariable(value = "id") Long id){
    	var pedido =pedidoServiceImpl.findById(id);   	
        return ResponseEntity.status(HttpStatus.OK).body(pedidoMapper.toPedidoDto(pedido));
    }
	
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza pedido status pelo ID")
	public ResponseEntity<PedidoDto> alteraStatusPedido(@PathVariable(value = "id")Long id,StatusPedido statusPedido){
    	Pedido pedidoUpdate = pedidoServiceImpl.findById(id);
    	var produto = pedidoUpdate.getProduto();
    	var comanda =pedidoUpdate.getComanda();
    	pedidoServiceImpl.alteraStatuspedido(pedidoUpdate, statusPedido);
    	
    	var pedidoDto = pedidoServiceImpl.montaRetornoPedidoDto(pedidoUpdate, produto, comanda);
    	
		return ResponseEntity.status(HttpStatus.OK).body(pedidoDto);
	}
	
	@GetMapping	    
	public ResponseEntity<Page<PedidoDto>> getStatusDePedidos(@RequestParam(required = false) StatusPedido status,
    		@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
			
        Page<PedidoDto> listaPedidoDto = pedidoServiceImpl.buscaListaPedido(status, pageable);
       
        return ResponseEntity.status(HttpStatus.OK).body(listaPedidoDto);
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoDto> deleta(@PathVariable(value = "id") Long id){
		pedidoServiceImpl.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();   	
    }
    
	@GetMapping("/cozinha")	    
	public ResponseEntity<List<PedidoCozinhaDto>> getPedidosCozinha(){			
        List<PedidoCozinhaDto> listaPedidoCozinha = pedidoServiceImpl.buscaPedidosCozinhao();
       
        return ResponseEntity.status(HttpStatus.OK).body(listaPedidoCozinha);
	}
    
    
    
    
    
}
