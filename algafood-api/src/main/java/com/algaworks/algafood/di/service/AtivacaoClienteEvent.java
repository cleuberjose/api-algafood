package com.algaworks.algafood.di.service;

import com.algaworks.algafood.di.modelo.Cliente;

public class AtivacaoClienteEvent {
	private Cliente cliente;

	public AtivacaoClienteEvent(Cliente cliente) {
		super();
		this.cliente = cliente;
	}
	public Cliente getCliente() {
		return cliente;
	}
	
	
}
