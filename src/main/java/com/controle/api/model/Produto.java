package com.controle.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
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
@Table(name ="tb_produto")
public class Produto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="nome_produto")
	private String nome;
	
	@Column(name="obs_produto")
	private String obeservacao;
	
	@Column(name="tipo_produto")
	private String tipo;
	
	@Column(name="valor_produto")
	private Double valor;
	
	@Column(name="status_produto")
	private Boolean status;

}
