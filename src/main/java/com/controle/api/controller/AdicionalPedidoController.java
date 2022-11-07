package com.controle.api.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import com.controle.api.dto.AdicionalPedidoDto;
import com.controle.api.dto.AdicionalPedidoInputDto;
import com.controle.api.service.AdicionalPedidoService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/adicional-produto")
@Api(value="API adicional de Produto")
public class AdicionalPedidoController {
	
	@Autowired
    private AdicionalPedidoService adicionalPedidoService;
	
	@PostMapping
	@ApiOperation(value="Cadastrar novo adicional em um produto ")
    public ResponseEntity<AdicionalPedidoDto> saveAdcProduto(@RequestBody @Valid AdicionalPedidoInputDto adicionalPedidoInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(adicionalPedidoService.save(adicionalPedidoInputDto, null));
    }

}
