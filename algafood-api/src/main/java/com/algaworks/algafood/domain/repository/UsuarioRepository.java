package com.algaworks.algafood.domain.repository;

import java.util.Optional;

import com.algaworks.algafood.domain.model.Usuario;

public interface UsuarioRepository extends CustomJpaRepository<Usuario, Integer>{

	Optional<Usuario> findByEmail(String email);

}
