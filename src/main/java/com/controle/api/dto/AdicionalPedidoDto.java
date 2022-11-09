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
public class AdicionalPedidoDto {
	
    private Long id;
	
	private ProdutoDto produto;
	
	private List<AdicionalDto> adicional;

	private String observacao;

}
