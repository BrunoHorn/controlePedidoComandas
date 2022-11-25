package com.controle.api.dto;

import java.time.LocalDateTime;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoCozinhaDto {
	
	private Long comandaId;
	
	private String nomeProduto;
	
	private String observacaoProduto;
	
	private LocalDateTime dataPedido ;
	
	private List<String> nomeAdicional;

}
