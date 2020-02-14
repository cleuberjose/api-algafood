package com.algaworks.algafood.api.model.input;

import javax.validation.constraints.NotNull;
import javax.validation.constraints.PositiveOrZero;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PedidoItemInput {
		
	@NotNull
	private Integer produtoId;
	
	@NotNull
	@PositiveOrZero
	private Integer quantidade;
	
	private String observacao;
}
