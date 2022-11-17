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
import com.controle.api.repository.ProdutoRepository;
import com.controle.api.service.ProdutoService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/produto")
@Api(value="API cadastro de Produto")
public class ProdutoController {
	  
	@Autowired
	private ProdutoService produtoService;
	
	@Autowired
	private ProdutoMapper produtoMapper;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	@ApiOperation(value="Cadastrar novo produto")
    public ResponseEntity<ProdutoDto> saveProduto(@RequestBody @Valid ProdutoInputDto produtoInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(produtoService.save(produtoInputDto, null));
    }
   
    @GetMapping
    public ResponseEntity<Page<ProdutoDto>> getListProduto(@RequestParam(required = false) Boolean status,
    		@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable	){
        
        Page<ProdutoDto> listaProdutoDto = produtoService.buscaListaproduto(status, pageable);
        
        return ResponseEntity.status(HttpStatus.OK).body(listaProdutoDto);               
    }
       
    @GetMapping("/{id}")
    @ApiOperation(value="Busca produto cadastrado pelo ID")
    public ResponseEntity<ProdutoDto> getProdutoId(@PathVariable(value = "id") Long id){
    	Produto produto =produtoService.findById(id);   	
        return ResponseEntity.status(HttpStatus.OK).body(produtoMapper.toProdutoDto(produto));
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza Produto cadastrado pelo ID")
    public ResponseEntity<ProdutoDto> updateProduto(@PathVariable(value = "id")Long id,@RequestBody @Valid ProdutoInputDto produtoDto){
        Produto produtoUpdate = produtoService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(produtoService.save(produtoDto, produtoUpdate.getId()));
    }
    
    @DeleteMapping("/{id}")
    @ApiOperation(value="Deleta produto pelo id")
	public void delete(Produto produto) {
    	produtoRepository.delete(produto);		
	}
	
}
