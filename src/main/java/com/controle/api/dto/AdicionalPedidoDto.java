package com.controle.api.dto;



import com.controle.api.model.Adicional;
import com.controle.api.model.Produto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdicionalPedidoDto {
	


    private Long id;
	
	private Produto produto;
	
	private Adicional adicional;

	private Long pedido;
	
	private String observacao;

}
