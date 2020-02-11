package com.algaworks.algafood.domain.exception;

public class ProdutoNaoEncontradoException extends EntidadeNaoEncontradaException  {
	private static final long serialVersionUID = 1L;
		
	
	public ProdutoNaoEncontradoException(String mensagem) {
		super(mensagem);
	}
	public ProdutoNaoEncontradoException(Integer idRestaurante, Integer idProduto) {
		super(String.format("Não existe cadastro de produto com o código %d para o restaurante de código %d", idProduto, idRestaurante));		
	}

}
