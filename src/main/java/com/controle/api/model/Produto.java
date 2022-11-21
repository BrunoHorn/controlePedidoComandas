package com.controle.api.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import com.controle.api.enumerado.TipoDeProduto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Builder
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name ="produto")
public class Produto {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
	
	@Column(name="nome")
	private String nome;
	
	@Column(name="observacao")
	private String obeservacao;
	
	@Column(name="tipo") //ENUMARADO
	@Enumerated(EnumType.STRING)
	private TipoDeProduto tipo;
	
	@Column(name="valor")
	private Double valor;
	
	@Column(name="status")
	private Boolean status;

}
