package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;

import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;

public interface ProdutoRepository extends CustomJpaRepository<Produto, Integer> {

	List<Produto> findByRestaurante(Restaurante restaurante);

	@Query("from Produto p WHERE p.restaurante.id=:idRestaurante AND p.id=:idProduto ")
	Optional<Produto> findById(Integer idRestaurante, Integer idProduto);

}
