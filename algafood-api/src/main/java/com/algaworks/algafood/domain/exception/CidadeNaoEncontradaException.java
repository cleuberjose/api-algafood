package com.algaworks.algafood.domain.exception;

public class CidadeNaoEncontradaException extends EntidadeNaoEncontradaException  {
	private static final long serialVersionUID = 1L;
		
	
	public CidadeNaoEncontradaException(String mensagem) {
		super(mensagem);
	}
	public CidadeNaoEncontradaException(Integer id) {
		super(String.format("Não existe cadastro de cidade com o código %d", id));		
	}

}
