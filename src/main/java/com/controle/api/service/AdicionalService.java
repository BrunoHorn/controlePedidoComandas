package com.controle.api.service;

import java.util.List;
import java.util.Objects;

import javax.transaction.Transactional;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.api.dto.AdicionalDto;
import com.controle.api.dto.AdicionalInputDto;
import com.controle.api.mapper.AdicionalMapper;
import com.controle.api.model.Adicional;
import com.controle.api.repositorie.AdicionalRepository;



@Service
public class AdicionalService {

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

	public List<AdicionalDto> findAll() {		
		return adicionalMapper.toAdicionalListDto(adicionalRepository.findAll());
	}

	public Adicional findById(Long id) {
		var adicionalOptional = adicionalRepository.findById(id);
		if(adicionalOptional.isEmpty()) {
			throw new RuntimeException("Não a adicionais cadastrados com esse ID");
		}
		return adicionalOptional.get();
	}
	
    @Transactional
	public void delete(Adicional adicional) {
    	adicionalRepository.delete(adicional);
		
	}
		

}
