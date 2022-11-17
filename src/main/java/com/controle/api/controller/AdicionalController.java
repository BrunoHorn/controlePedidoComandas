package com.controle.api.controller;

import java.util.ArrayList;
import java.util.List;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
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
import com.controle.api.dto.AdicionalDto;
import com.controle.api.dto.AdicionalInputDto;
import com.controle.api.mapper.AdicionalMapper;
import com.controle.api.model.Adicional;
import com.controle.api.repository.AdicionalRepository;
import com.controle.api.service.AdicionalService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

import org.springframework.data.domain.Sort;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/adicional")
@Api(value="API cadastro de Adicional")
public class AdicionalController {
	
	@Autowired
    private AdicionalService adicionalService;
	
	@Autowired
	private AdicionalMapper adicionalMapper;
	
	@Autowired
	private AdicionalRepository adicionalRepository;
	
	
	@PostMapping
	@ApiOperation(value="Cadastrar novo adicional")
    public ResponseEntity<AdicionalDto> saveAdicional(@RequestBody @Valid AdicionalInputDto adicionalInputDto){		
        return ResponseEntity.status(HttpStatus.CREATED).body(adicionalService.save(adicionalInputDto, null));
    }
	
   /* @GetMapping
    @ApiOperation(value="Busca lista de adicionais cadastrados")
    public ResponseEntity<List<AdicionalDto>> getListAdicionais(@RequestParam(required = false) Boolean status){
        List<Adicional> adicionais= new ArrayList<>();
        adicionais = adicionalService.buscaListaAdicional(status);        
        return ResponseEntity.status(HttpStatus.OK).body(adicionalMapper.toAdicionalListDto(adicionais));
                
    }*/
	
   /* @GetMapping
    public ResponseEntity<Page<AdicionalDto>> getListAdicionais(@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.ASC) 
    								Pageable pageable){ teste
        return ResponseEntity.status(HttpStatus.OK).body(adicionalService.findAll(pageable));
    }
	
    */
    @GetMapping
    @ApiOperation(value="Busca lista de adicionais cadastrados")
    public ResponseEntity<Page<AdicionalDto>> getListAdicionais(@RequestParam(required = false) Boolean status,
    		@PageableDefault(page = 0, size = 2, sort = "id", direction = Sort.Direction.DESC)Pageable pageable	){
        
        Page<AdicionalDto> listaAdicionaisDto = adicionalService.buscaListaAdicional(status, pageable);
        
        return ResponseEntity.status(HttpStatus.OK).body(listaAdicionaisDto);
                
    }
    
         
    
	
    
    @GetMapping("/{id}")
    @ApiOperation(value="Busca adicional cadastrado pelo ID")
    public ResponseEntity<AdicionalDto> getAdicionalid(@PathVariable(value = "id") Long id){
    	Adicional adicional =adicionalService.findById(id);   	
        return ResponseEntity.status(HttpStatus.OK).body(adicionalMapper.toAdicionalDto(adicional));
    }
    
    @PutMapping("/{id}")
    @ApiOperation(value="Atualiza adicional cadastrado pelo ID")
    public ResponseEntity<AdicionalDto> updateAdicional(@PathVariable(value = "id")Long id,@RequestBody @Valid AdicionalInputDto adicionalDto){
        Adicional adicionalUpdate = adicionalService.findById(id);
        return ResponseEntity.status(HttpStatus.OK).body(adicionalService.save(adicionalDto, adicionalUpdate.getId()));
    }
    
    //@DeleteMapping("/{id}")
   // @ApiOperation(value="Deleta adicional pelo id")
	//public void delete(Adicional adicional) {
   // 	adicionalRepository.delete(adicional);		
	//}
   
    @DeleteMapping("/{id}")
    public ResponseEntity<Adicional> deleta(@PathVariable(value = "id") Long id){
    	try {
    		Adicional adicional =adicionalService.findById(id); 
    		if (adicional!= null) {
    			adicionalRepository.delete(adicional);
    			return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
    		}
    		return ResponseEntity.status(HttpStatus.NOT_FOUND).build();   		
    	} catch(DataIntegrityViolationException e) {
    		
    		return null;
    	}
    	
    }
    
	


}
