package com.controle.api.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoretornoStatusDto {

	private Long pedidoId;
	
	private String pedidoObs;
	
	private LocalDateTime dataPedido;
	
	private Long comandaId;
	
	private String comandaObs;
	
	private Long produtoId;
	
	private String produtoNome;
	
}
