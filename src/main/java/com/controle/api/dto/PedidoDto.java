package com.controle.api.dto;

import java.time.LocalDateTime;


import com.controle.api.enumerado.StatusPedido;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class PedidoDto {
	

    private Long id;
	
	private String observacao ;
	
	private StatusPedido status;
	
	private LocalDateTime dataAtualizacao ;
	
	private Long comanda;
	
	private PrudutoAdcPedidoDto produto;

}
