package com.controle.api.service.Impl;

import java.util.Objects;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.controle.api.dto.ComandaDto;
import com.controle.api.dto.ComandaInputDto;
import com.controle.api.exception.EntidadeEmUsoException;
import com.controle.api.exception.EntidadeNaoEncontradaException;
import com.controle.api.mapper.ComandaMapper;
import com.controle.api.model.Comanda;
import com.controle.api.repository.ComandaRepository;
import com.controle.api.service.ComandaService;

@Service
public class ComandaServiceImpl implements ComandaService{

	@Autowired
	private ComandaMapper comandaMapper;
	
	@Autowired
	private ComandaRepository comandaRepository;
	
	@Override
	public ComandaDto save(@Valid ComandaInputDto comandaInputDto, Long id) {
		var comanda =comandaMapper.toComanda(comandaInputDto);
		
		if(Objects.nonNull(id)) {
			comanda.setId(id);
		}
		comandaRepository.save(comanda);
		var comandaDto = comandaMapper.toComandaDto(comanda);
		return comandaDto ;
	}

	@Override
	public Comanda findById(Long id) {
		var comandaOptional = comandaRepository.findById(id);
		if(comandaOptional.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não a Comanda cadastrada com esse ID");
		}
		return comandaOptional.get();
	}

	/*private ComandaDto trocaStatusComanda(@Valid ComandaencerradaDto comandaencerradaDto, Long id) {
		var comandaOptional = comandaRepository.findById(id);
		if(comandaOptional.isEmpty()) {
			throw new EntidadeNaoEncontradaException("Não há Comanda cadastrada com esse ID");
		}
		var comanda = comandaOptional.get();
			comanda.setStatus(comandaencerradaDto.getStatusComanda());
			comandaRepository.save(comanda);
			var comandaDto = comandaMapper.toComandaDto(comanda);
		return comandaDto;
	}*/

	/*private List<ComandaDto> comandaFinalizada() {
		 List<Comanda> comanda = comandaRepository.findAll();
		 List<Comanda> comandaFinalizada = new ArrayList<>();
		 for (Comanda cm : comanda) {
			 if (!cm.getStatus()) {
				 comandaFinalizada.add(cm);
			 }
		 }		 
		 return comandaMapper.toComandaListDto(comandaFinalizada);
	}*/

	/*private List<ComandaDto> comandaAbertas() {
		 List<Comanda> comanda = comandaRepository.findAll();
		 List<Comanda> comandaFinalizada = new ArrayList<>();
		 for (Comanda cm : comanda) {
			 if (cm.getStatus()) {
				 comandaFinalizada.add(cm);
			 }
		 }		 
		 return comandaMapper.toComandaListDto(comandaFinalizada);
	}
	*/
	@Override
    public Page<ComandaDto> buscaListaComanda(Boolean status, Pageable pageable){
    	if (status == null) {
    		status= true;    		
   	 	} 
  
   	 	Page<Comanda> page = comandaRepository.findByStatus(status, pageable);
   	 	Page<ComandaDto> comandasDto = page.map(pagedto -> comandaMapper.toComandaDto(pagedto));
   	 	return comandasDto;
   }
    
	@Override
	public void excluir(Long id) {
		try { 	
			var comanda = findById(id);
			comandaRepository.delete(comanda);     
		} catch(DataIntegrityViolationException e){
			throw new EntidadeEmUsoException("Comanda está em uso , só pode ser desativada");
		}
    }






	




}
