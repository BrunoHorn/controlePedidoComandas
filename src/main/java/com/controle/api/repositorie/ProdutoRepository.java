package com.controle.api.repositorie;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.controle.api.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{

}
