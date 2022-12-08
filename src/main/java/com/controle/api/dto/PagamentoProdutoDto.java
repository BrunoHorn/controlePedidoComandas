package com.controle.api.dto;

import java.util.List;

import com.controle.api.enumerado.StatusPedido;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PagamentoProdutoDto {
	
	private String nomeProduto;
	
	private Double valorProduto;
		
	private List<PagamentoAdicionalDto> pagamentoAdicionalDto;
	
	private StatusPedido statusPedido;
	
	
}
