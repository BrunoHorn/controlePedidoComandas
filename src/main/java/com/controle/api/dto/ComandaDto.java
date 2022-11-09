package com.controle.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ComandaDto {
	
    private Long id;
	
	private String obeservacao; 
	
	private Boolean statusComanda;

}