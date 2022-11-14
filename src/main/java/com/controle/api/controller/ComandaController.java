package com.controle.api.controller;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
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
import com.controle.api.repository.ComandaRepository;
import com.controle.api.service.ComandaService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/comanda")
@Api(value="API cadastro de comanda")
public class ComandaController {
	
	@Autowired
	private ComandaService comandaService;
	
	@Autowired
	private ComandaMapper comandaMapper;
	
	@Autowired
	private ComandaRepository comandaRepository;
	
	@PostMapping
	@ApiOperation(value="Cadastrar nova comanda")
    public ResponseEntity<ComandaDto> saveComanda(@RequestBody @Valid ComandaInputDto comandaInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(comandaService.save(comandaInputDto, null));
    }

    @GetMapping("/{id}")
    @ApiOperation(value="Busca comandas cadastrado pelo ID")
    public ResponseEntity<ComandaDto> getComandaId(@PathVariable(value = "id") Long id){
    	Comanda comanda =comandaService.findById(id);   	
        return ResponseEntity.status(HttpStatus.OK).body(comandaMapper.toComandaDto(comanda));
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza comanda cadastrado pelo ID")
    public ResponseEntity<ComandaDto> updateComanda(@PathVariable(value = "id")Long id,@RequestBody @Valid ComandaInputDto comandaInputDto){
        Comanda comandaUpdate = comandaService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(comandaService.save(comandaInputDto, comandaUpdate.getId()));
    } 
    
    @DeleteMapping("/{id}")
    @ApiOperation(value="Deleta comanda pelo id")
	public void delete(Comanda comanda) {
    	comandaRepository.delete(comanda);		
	}
    
    
    @GetMapping
    public ResponseEntity<List<ComandaDto>> getListProdutos(@RequestParam(required = false) Boolean status){
    	   List<Comanda> comandas= new ArrayList<>();
    	   comandas = comandaService.buscaListaComanda(status);
    	
    	return ResponseEntity.status(HttpStatus.OK).body(comandaMapper.toComandaListDto(comandas));
    }
}
