package com.controle.api.service.Impl;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.controle.api.dto.AdicionalDto;
import com.controle.api.dto.AdicionalInputDto;
import com.controle.api.exception.EntidadeEmUsoException;
import com.controle.api.exception.EntidadeNaoEncontradaException;
import com.controle.api.mapper.AdicionalMapper;
import com.controle.api.model.Adicional;
import com.controle.api.repository.AdicionalRepository;
import com.controle.api.service.AdicionalService;

@Service
public class AdicionalServiceImpl implements AdicionalService {

	@Autowired
	private AdicionalRepository adicionalRepository;
	
	@Autowired
	private AdicionalMapper adicionalMapper;
	
	public AdicionalDto save(@Valid AdicionalInputDto AdicionalInputDto, Long id) {
		var adicional = adicionalMapper.toAdicional(AdicionalInputDto); 
		
        if (Objects.nonNull(id)) {
        	adicional.setId(id);
        }		
		adicional = adicionalRepository.save(adicional);		
		AdicionalDto adicionalDto = adicionalMapper.toAdicionalDto(adicional);		
	return adicionalDto;
	} 

	public Page<AdicionalDto> findAll(Pageable pageable) {		
		 Page<Adicional> adicionais = adicionalRepository.findAll(pageable);
		
		Page<AdicionalDto> adicionaisDto = adicionais.map(teste -> adicionalMapper.toAdicionalDto(teste));
		  
		  return adicionaisDto;
	}


	public Adicional findById(Long id) {
		var adicionalOptional = adicionalRepository.findById(id);
		
		if(adicionalOptional.isEmpty()) {			
			throw new EntidadeNaoEncontradaException("Não a adicionais cadastrados com esse ID");
		}
		return adicionalOptional.get();
	}
	
	public void excluir(Long id) {
		try { 	
			var adicional = findById(id);
			adicionalRepository.delete(adicional);     
		} catch(DataIntegrityViolationException e){
		throw new EntidadeEmUsoException("Adicional está em uso , só pode ser desativado");
		}
    }
        
    public Page<AdicionalDto> buscaListaAdicional(Boolean status, Pageable pageable){
    	 if (status == null) {
    		 status= true;    		
    	 } 
  
    	 Page<Adicional> page = adicionalRepository.findByStatus(status, pageable);
    	 Page<AdicionalDto> adicionaisDto = page.map(pagedto -> adicionalMapper.toAdicionalDto(pagedto));
    	 
    	 return adicionaisDto;
    }
    
    
    
    
  
		

}