package com.controle.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdicionalInputDto {
	
	private String  nome;
	
	private Double valor;
	
	private Boolean status;
}
