package com.controle.api.model;

import java.time.LocalDateTime;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="pedido")
public class Pedido {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	private String observacao ;
	
	@Column(name="data_atualizacao_pedido")
	private LocalDateTime dataAtualizacao ;
	
	@ManyToOne
	@JoinColumn(name="comanda_id")
	private Comanda comanda;
	
	@ManyToOne
	@JoinColumn(name="produto_id")
	private Produto produto;
		
	/*
	

	@ManyToOne
	@JoinColumn(name="usuario_id")
	private Usuario usuario;
	
	@ManyToOne
	@JoinColumn(name="pagamento_id")
	private Pagamento pagamento;
	*/


}
