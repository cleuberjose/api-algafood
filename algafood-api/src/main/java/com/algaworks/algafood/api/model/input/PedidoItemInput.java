package com.algaworks.algafood.api.model.input;

import java.math.BigDecimal;

import javax.validation.Valid;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoItemInput {
	
	@Valid
	@NotNull
	private PedidoIdInput pedido;
	
	@Valid
	@NotNull
	private ProdutoIdInput produto;
	
	@NotNull
	private Integer quantidade;
	
	@NotNull
	private BigDecimal precoUnitario;
	
	@NotNull
	private BigDecimal precoTotal;
	
	@NotBlank
	private String observacao;
}
