package com.controle.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class UsuarioInputDto {
	
	private String nome ;
	
	private String senha;
	
	private String tipoPermissao;
	
	private Boolean status;

}
