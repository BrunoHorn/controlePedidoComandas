package com.controle.api.dto;

import com.controle.api.enumerado.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoInputDto {
	
	private String observacao ;
	
	private StatusPedido status;
		
	private Long comandaId;
	
	private Long produtoId;
}
