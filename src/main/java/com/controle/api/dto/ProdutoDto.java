package com.controle.api.dto;

import com.controle.api.enumerado.TipoDeProduto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class ProdutoDto {
	
	private Long id;
	
	private String nome;

	private String obeservacao;

	private TipoDeProduto tipo;
	
	private Double valor;

	private Boolean status;
}
