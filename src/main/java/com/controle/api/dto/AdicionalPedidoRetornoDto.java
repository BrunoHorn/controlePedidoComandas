package com.controle.api.dto;



import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class AdicionalPedidoRetornoDto {
	
	private Long Id;

	private Long produtoId;
	
	private AdcPedidoRetornoDto adicional;

}
