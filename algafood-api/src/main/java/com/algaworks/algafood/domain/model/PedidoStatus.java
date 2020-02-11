package com.algaworks.algafood.domain.model;

public enum PedidoStatus {
	CRIADO("Criado"), CONFIRMADO("Confirmado"), ENTREGUE("Entregue"), CANCELADO("Cancelado");
	
	private String descricao;

	private PedidoStatus(String descricao) {
		this.descricao=descricao;
	}
	public String getDescricao() {
		return descricao;
	}
}
