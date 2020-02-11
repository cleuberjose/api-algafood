package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteModel {

	private Integer id;
	private String nome;
	private BigDecimal precoFrete;
	private Boolean ativo;
	private Boolean aberto;
	private CozinhaModel cozinha;
	private EnderecoModel endereco;
	
}