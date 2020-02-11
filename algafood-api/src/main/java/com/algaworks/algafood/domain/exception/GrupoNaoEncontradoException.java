package com.algaworks.algafood.domain.exception;

public class GrupoNaoEncontradoException extends EntidadeNaoEncontradaException{
	private static final long serialVersionUID = 1L;
	
	public GrupoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	public GrupoNaoEncontradoException(Integer id) {
		super(String.format("O Grupo de código %d não foi encontrado",id));
	}

}
