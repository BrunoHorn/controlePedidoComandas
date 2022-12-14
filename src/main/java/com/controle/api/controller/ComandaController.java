package com.controle.api.controller;

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

import com.controle.api.dto.ComandaDto;
import com.controle.api.dto.ComandaInputDto;
import com.controle.api.mapper.ComandaMapper;
import com.controle.api.model.Comanda;
import com.controle.api.service.Impl.ComandaServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comanda")
@Api(value="API cadastro de comanda")
public class ComandaController {
	
	@Autowired
	private ComandaServiceImpl comandaServiceImpl;
	
	@Autowired
	private ComandaMapper comandaMapper;
		
	@PostMapping
	@ApiOperation(value="Cadastrar nova comanda")
    public ResponseEntity<ComandaDto> saveComanda(@RequestBody @Valid ComandaInputDto comandaInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(comandaServiceImpl.save(comandaInputDto, null));
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Busca comandas cadastrado pelo ID")
    public ResponseEntity<ComandaDto> getComandaId(@PathVariable(value = "id") Long id){
    	Comanda comanda =comandaServiceImpl.findById(id);   	
        return ResponseEntity.status(HttpStatus.OK).body(comandaMapper.toComandaDto(comanda));
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza comanda cadastrado pelo ID")
    public ResponseEntity<ComandaDto> updateComanda(@PathVariable(value = "id")Long id,@RequestBody @Valid ComandaInputDto comandaInputDto){
        Comanda comandaUpdate = comandaServiceImpl.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(comandaServiceImpl.save(comandaInputDto, comandaUpdate.getId()));
    } 
    
    @DeleteMapping("/{id}")
    @ApiOperation(value="Deleta comanda pelo id")
    public ResponseEntity<ComandaDto> deleta(@PathVariable(value = "id") Long id){
		comandaServiceImpl.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();   	 
    }
    
    @GetMapping
    public ResponseEntity<Page<ComandaDto>> getListComandas(@RequestParam(required = false) Boolean status,
    		@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable	){
        
        Page<ComandaDto> listaComandaDto = comandaServiceImpl.buscaListaComanda(status, pageable);        
        return ResponseEntity.status(HttpStatus.OK).body(listaComandaDto);                
    }
    
    
}
