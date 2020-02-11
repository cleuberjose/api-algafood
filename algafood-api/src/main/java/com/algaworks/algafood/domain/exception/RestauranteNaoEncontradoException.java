package com.algaworks.algafood.domain.exception;

public class RestauranteNaoEncontradoException extends EntidadeNaoEncontradaException  {
	private static final long serialVersionUID = 1L;
			
	public RestauranteNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	public RestauranteNaoEncontradoException(Integer id) {
		super(String.format("Não existe cadastro de restaurante com o código %d",id));
	}

}
