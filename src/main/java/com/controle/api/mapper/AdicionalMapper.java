package com.controle.api.mapper;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Component;

import com.controle.api.dto.AdicionalDto;
import com.controle.api.dto.AdicionalInputDto;
import com.controle.api.model.Adicional;



@Component
public class AdicionalMapper {
	
	public Adicional toAdicional(AdicionalInputDto adicionalDto) {
		var adicional = new Adicional();
		 BeanUtils.copyProperties(adicionalDto, adicional); 
		 return adicional;
	}
	
	public AdicionalDto toAdicionalDto(Adicional adicional) {
		var adicionalDto = new AdicionalDto();
		 BeanUtils.copyProperties(adicional, adicionalDto); 
		 return adicionalDto;
	}
	
	public List<AdicionalDto> toAdicionalListDto(List<Adicional> adicional) {
		List<AdicionalDto> adicionalListDto = new ArrayList<>();		
		for (Adicional ad : adicional) {
			var adicionalDto = new AdicionalDto();
			adicionalDto.setId(ad.getId());
			adicionalDto.setNome(ad.getNome());
			adicionalDto.setValor(ad.getValor());
			adicionalDto.setStatus(ad.getStatus());
			adicionalListDto.add(adicionalDto);
		}				 
		 return adicionalListDto;
	}
	


}
