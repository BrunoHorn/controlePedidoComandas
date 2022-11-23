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

import com.controle.api.dto.PedidoDto;
import com.controle.api.dto.PedidoInputDto;
import com.controle.api.enumerado.StatusPedido;
import com.controle.api.mapper.PedidoMapper;
import com.controle.api.model.Pedido;
import com.controle.api.service.PedidoService;

import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/pedido")
public class PedidoController {
	
	@Autowired
	private PedidoService pedidoService;
	
	@Autowired
	private PedidoMapper pedidoMapper;
		
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
	public ResponseEntity<Page<PedidoDto>> getStatusDePedidos(@RequestParam(required = false) StatusPedido status,
    		@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable){
			
        Page<PedidoDto> listaPedidoDto = pedidoService.buscaListaPedido(status, pageable);
       
        return ResponseEntity.status(HttpStatus.OK).body(listaPedidoDto);
	}
	
    @DeleteMapping("/{id}")
    public ResponseEntity<PedidoDto> deleta(@PathVariable(value = "id") Long id){
		pedidoService.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();   	
    }
    
	@GetMapping("/cozinha")	    
	public ResponseEntity<List<Object>> getPedidosCozinha(){
			
        List<Object> listaPedidoCozinha = pedidoService.buscaPedidosCozinhao();
       
        return ResponseEntity.status(HttpStatus.OK).body(listaPedidoCozinha);
	}
    
    
    
    
    
}
