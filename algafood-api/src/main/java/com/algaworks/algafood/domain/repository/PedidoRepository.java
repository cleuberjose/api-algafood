package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Pedido;

@Repository
public interface PedidoRepository extends CustomJpaRepository<Pedido, Integer>{

	@Query("from Pedido p JOIN FETCH p.usuario usuario JOIN FETCH p.restaurante restaurante"
			+ " JOIN FETCH restaurante.cozinha cozinha")
	public List<Pedido> findAll();
	
	@Query("from Pedido p WHERE p.codigo=:codigo")
	public Optional<Pedido> findByCodigo(String codigo);
}
