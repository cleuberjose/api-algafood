package com.algaworks.algafood.domain.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.algaworks.algafood.domain.model.Cozinha;

@Repository
public interface CozinhaRepository extends JpaRepository<Cozinha, Integer>{

//	List<Cozinha> findByNome(String nome);
	List<Cozinha> findByNomeContainingIgnoreCase(String nome);
//	List<Cozinha> findFirstByNomeContainingIgnoreCase(String nome);
//	List<Cozinha> findTop2ByNomeContainingIgnoreCase(String nome);
	Optional<Cozinha> findFirstByNomeContainingIgnoreCase(String nome);
}
