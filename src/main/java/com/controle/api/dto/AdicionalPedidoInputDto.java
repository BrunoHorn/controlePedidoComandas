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
public class AdicionalPedidoInputDto {
	
	private Long produtoId;
	
	private List<Long> adicionalId;

	private Long pedidoId;
	
	private String observacao;

}
