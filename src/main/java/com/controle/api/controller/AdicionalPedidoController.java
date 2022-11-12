package com.controle.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.controle.api.dto.AdicionalPedidoInputDto;
import com.controle.api.dto.AdicionalPedidoRetornoDto;
import com.controle.api.model.AdicionalPedido;
import com.controle.api.repository.AdicionalPedidoRepository;
import com.controle.api.service.AdicionalPedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/adicional-pedido")
@Api(value="API adicional de pedido")
public class AdicionalPedidoController {
	
	@Autowired
    private AdicionalPedidoService adicionalPedidoService;
	
	@Autowired
	private AdicionalPedidoRepository adicionalPedidoRepository;
	
	@PostMapping
	@ApiOperation(value="Cadastrar novo adicional em um pedido ")
    public ResponseEntity<List<AdicionalPedidoRetornoDto>> saveAdcProduto(@RequestBody @Valid AdicionalPedidoInputDto adicionalPedidoInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(adicionalPedidoService.save(adicionalPedidoInputDto, null));
    }

	
    @DeleteMapping("/{id}")
    @ApiOperation(value="DeletaAdicional pelo id")
	public void delete(AdicionalPedido adicionalPedido) {
    	adicionalPedidoRepository.delete(adicionalPedido);		
	}
}
