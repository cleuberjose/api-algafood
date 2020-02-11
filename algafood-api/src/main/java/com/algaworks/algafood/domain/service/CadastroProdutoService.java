package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.algaworks.algafood.domain.exception.ProdutoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;

@Service
public class CadastroProdutoService {

	@Autowired
	private ProdutoRepository produtoRepository;
	
	public List<Produto> listar(Restaurante restaurante){
		return produtoRepository.findByRestaurante(restaurante);
	}
	public Produto buscarOuFalhar(Integer idRestaurante, Integer idProduto) {
		return produtoRepository.findById(idRestaurante, idProduto).orElseThrow(()
				-> new ProdutoNaoEncontradoException(idRestaurante, idProduto));
	}
	public Produto salvar(Produto produto) {
		return produtoRepository.save(produto);
	}
}
