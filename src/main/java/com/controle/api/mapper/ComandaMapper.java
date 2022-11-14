package com.controle.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.controle.api.dto.ComandaDto;
import com.controle.api.dto.ComandaInputDto;
import com.controle.api.model.Comanda;



@Component
public class ComandaMapper {
	
	public Comanda toComanda(ComandaInputDto comandaInputDto) {
		var comanda = new Comanda();
		 BeanUtils.copyProperties(comandaInputDto, comanda); 
		 return comanda;
	}
	
	public ComandaDto toComandaDto(Comanda comanda) {
		var comandaDto = new ComandaDto();
		 BeanUtils.copyProperties(comanda, comandaDto); 
		 return comandaDto;
	}
	
	public List<ComandaDto> toComandaListDto(List<Comanda> comanda){
		List <ComandaDto> ComandaListDto = new ArrayList<>();
		for (Comanda cm : comanda) {
			var comandaDto = new ComandaDto();
			comandaDto.setId(cm.getId());
			comandaDto.setStatusComanda(cm.getStatus());
			
			ComandaListDto.add(comandaDto);
			
		}		
		return ComandaListDto;
	}
	

}
