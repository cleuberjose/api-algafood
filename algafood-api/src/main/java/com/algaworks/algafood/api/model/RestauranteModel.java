package com.algaworks.algafood.api.model;

import java.math.BigDecimal;

import com.algaworks.algafood.api.model.view.RestauranteView;
import com.fasterxml.jackson.annotation.JsonView;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class RestauranteModel {

	@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private Integer id;
	
	@JsonView({RestauranteView.Resumo.class, RestauranteView.ApenasNome.class})
	private String nome;
	
	@JsonView(RestauranteView.Resumo.class)
	private BigDecimal taxaFrete;
	
	
	private Boolean ativo;
	private Boolean aberto;
	
	@JsonView(RestauranteView.Resumo.class)
	private CozinhaModel cozinha;
	private EnderecoModel endereco;
	
}