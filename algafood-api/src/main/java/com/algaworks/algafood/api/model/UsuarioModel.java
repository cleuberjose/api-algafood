package com.algaworks.algafood.api.model;

import java.time.OffsetDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UsuarioModel {
	private Integer id;
	private String nome;
	private String email;
	private String senha;
	private OffsetDateTime dataCadastro;

}
