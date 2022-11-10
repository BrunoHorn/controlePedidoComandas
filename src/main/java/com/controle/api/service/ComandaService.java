package com.controle.api.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.controle.api.dto.ComandaDto;
import com.controle.api.dto.ComandaInputDto;
import com.controle.api.dto.ComandaencerradaDto;
import com.controle.api.mapper.ComandaMapper;
import com.controle.api.model.Comanda;
import com.controle.api.repository.ComandaRepository;

@Service
public class ComandaService {

	@Autowired
	private ComandaMapper comandaMapper;
	
	@Autowired
	private ComandaRepository comandaRepository;
	
	public ComandaDto save(@Valid ComandaInputDto comandaInputDto, Long id) {
		var comanda =comandaMapper.toComanda(comandaInputDto);
		
		if(Objects.nonNull(id)) {
			comanda.setId(id);
		}
		comandaRepository.save(comanda);
		var comandaDto = comandaMapper.toComandaDto(comanda);
		return comandaDto ;
	}



	public Comanda findById(Long id) {
		var comandaOptional = comandaRepository.findById(id);
		if(comandaOptional.isEmpty()) {
			throw new RuntimeException("Não a Comanda cadastrada com esse ID");
		}
		return comandaOptional.get();
	}


	public ComandaDto trocaStatusComanda(@Valid ComandaencerradaDto comandaencerradaDto, Long id) {
		var comandaOptional = comandaRepository.findById(id);
		if(comandaOptional.isEmpty()) {
			throw new RuntimeException("Não a Comanda cadastrada com esse ID");
		}
		var comanda = comandaOptional.get();
			comanda.setStatusComanda(comandaencerradaDto.getStatusComanda());
			comandaRepository.save(comanda);
			var comandaDto = comandaMapper.toComandaDto(comanda);
		return comandaDto;
	}

	public List<ComandaDto> comandaFinalizada() {
		 List<Comanda> comanda = comandaRepository.findAll();
		 List<Comanda> comandaFinalizada = new ArrayList<>();
		 for (Comanda cm : comanda) {
			 if (!cm.getStatusComanda()) {
				 comandaFinalizada.add(cm);
			 }
		 }		 
		 return comandaMapper.toComandaListDto(comandaFinalizada);
	}

	public List<ComandaDto> comandaAbertas() {
		 List<Comanda> comanda = comandaRepository.findAll();
		 List<Comanda> comandaFinalizada = new ArrayList<>();
		 for (Comanda cm : comanda) {
			 if (cm.getStatusComanda()) {
				 comandaFinalizada.add(cm);
			 }
		 }		 
		 return comandaMapper.toComandaListDto(comandaFinalizada);
	}


	




}
