package com.algaworks.algafood.domain.model;

import java.util.Arrays;
import java.util.List;

public enum PedidoStatus {
	CRIADO("Criado"), CONFIRMADO("Confirmado",CRIADO), ENTREGUE("Entregue", CONFIRMADO), CANCELADO("Cancelado", CRIADO);
	
	private String descricao;
	private List<PedidoStatus> statusAnteriores;
	private PedidoStatus(String descricao, PedidoStatus ...statusAnteriores ) {
		this.descricao=descricao;
		this.statusAnteriores=Arrays.asList(statusAnteriores);
	}
	public String getDescricao() {
		return descricao;
	}
	public List<PedidoStatus> getStatusAnteriores() {
		return statusAnteriores;
	}
	public boolean naoPodeAlterarPara(PedidoStatus pedidoStatus) {
		return !pedidoStatus.statusAnteriores.contains(this);
		
	}
	
}
