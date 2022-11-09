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
public class AdicionalPedidoRetornoDto {
	
    private Long PedidoId;
	
	private PrudutoAdcPedidoDto produto;
	
	private List<AdcPedidoRetornoDto> adicional;

}
