package com.controle.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoInputDto {
	
	private String nome;

	private String obeservacao;

	private String tipo;

	private Double valor;

	private Boolean status;

}
