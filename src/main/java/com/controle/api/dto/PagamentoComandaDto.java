package com.controle.api.dto;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagamentoComandaDto {
	private Long comandaId;
	
	private List<PagamentoProdutoDto> produtoPagamentoDto;
	
	private Double valorTotal;
}
