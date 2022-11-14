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
@Table(name ="comanda")
public class Comanda {
	
	@Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
		
	@Column(name="status_comanda")
	private Boolean status;

}
