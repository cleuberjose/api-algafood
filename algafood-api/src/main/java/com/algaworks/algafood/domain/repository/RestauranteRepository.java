package com.algaworks.algafood.domain.repository;

import java.math.BigDecimal;
import java.util.List;

import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Restaurante;

@Repository
public interface RestauranteRepository extends CustomJpaRepository<Restaurante, Integer>, 
	RestauranteRepositoryQueries, JpaSpecificationExecutor<Restaurante>{
	
	@Query("from Restaurante r JOIN FETCH r.cozinha "
			+ "LEFT JOIN FETCH r.endereco.cidade cidade LEFT JOIN FETCH cidade.estado")
	List<Restaurante> findAll();
	
	List<Restaurante> queryByTaxaFreteBetween(BigDecimal valorInicio, BigDecimal balorFim);
	
	@Query("from Restaurante WHERE nome like %:nome% AND cozinha.id=:id")
	List<Restaurante> pesquisarPorNomeCozinha(String nome, @Param("id") Integer idCozinha);
//	List<Restaurante> findByNomeContainingAndCozinhaId(String nome, Integer idCozinha);
	List<Restaurante> find(String nome, BigDecimal valorInicio, BigDecimal valorFim);
}
