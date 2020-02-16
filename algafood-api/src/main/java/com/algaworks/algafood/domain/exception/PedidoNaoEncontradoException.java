package com.algaworks.algafood.domain.exception;

public class PedidoNaoEncontradoException extends EntidadeNaoEncontradaException  {
	private static final long serialVersionUID = 1L;
		
	public PedidoNaoEncontradoException(String codigo) {
		super(String.format("Não existe cadastro de pedido com o código %s", codigo));		
	}

}
