package com.controle.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
public class PedidoCozinha {
	
	@Column(name="id")
	private Pedido id;

	@Column(name="idComanda")
	private Comanda ComandaId;

	@Column(name="nome")
	private String nomeProduto;

	@Column(name="observacao")
	private String observacaoProduto;

	@Column(name="data_atualizacao")
	private LocalDateTime dataPedido ;

	@Column(name="nome_adicional")
	private String nomeAdicional;

}
