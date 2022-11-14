package com.controle.api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import com.controle.api.model.Produto;

@Repository
public interface ProdutoRepository extends JpaRepository<Produto, Long>{
	
	@Query(value ="select *from produto where status_produto = :status ", nativeQuery = true)
	List<Produto> buscaListaPdt(Boolean status);

}
