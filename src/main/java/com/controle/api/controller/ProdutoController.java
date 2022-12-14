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

import com.controle.api.dto.ProdutoDto;
import com.controle.api.dto.ProdutoInputDto;
import com.controle.api.mapper.ProdutoMapper;
import com.controle.api.model.Produto;
import com.controle.api.service.Impl.ProdutoServiceImpl;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/produto")
@Api(value="API cadastro de Produto")
public class ProdutoController {
	  
	@Autowired
	private ProdutoServiceImpl produtoServiceImpl;
	
	@Autowired
	private ProdutoMapper produtoMapper;
	
	@PostMapping
	@ApiOperation(value="Cadastrar novo produto")
    public ResponseEntity<ProdutoDto> saveProduto(@RequestBody @Valid ProdutoInputDto produtoInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoServiceImpl.save(produtoInputDto, null));
    }
   
    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> getListProduto(@RequestParam(required = false) Boolean status,
    		@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable	){
        
        Page<ProdutoDto> listaProdutoDto = produtoServiceImpl.buscaListaproduto(status, pageable);
        
        return ResponseEntity.status(HttpStatus.OK).body(listaProdutoDto);               
    }
       
    @GetMapping("/{id}")
    @ApiOperation(value="Busca produto cadastrado pelo ID")
    public ResponseEntity<ProdutoDto> getProdutoId(@PathVariable(value = "id") Long id){
    	Produto produto =produtoServiceImpl.findById(id);   	
        return ResponseEntity.status(HttpStatus.OK).body(produtoMapper.toProdutoDto(produto));
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza Produto cadastrado pelo ID")
    public ResponseEntity<ProdutoDto> updateProduto(@PathVariable(value = "id")Long id,@RequestBody @Valid ProdutoInputDto produtoDto){
        Produto produtoUpdate = produtoServiceImpl.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoServiceImpl.save(produtoDto, produtoUpdate.getId()));
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<ProdutoDto> deleta(@PathVariable(value = "id") Long id){
		produtoServiceImpl.excluir(id);
		return ResponseEntity.status(HttpStatus.NO_CONTENT).build();   	
    }
}
