package com.algaworks.algafood.domain.exception;

public class EstadoNaoEncontradoException extends EntidadeNaoEncontradaException  {
	private static final long serialVersionUID = 1L;
			
	public EstadoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	public EstadoNaoEncontradoException(Integer id) {
		super(String.format("O Estado de código %d não foi encontrado",id));
	}

}
