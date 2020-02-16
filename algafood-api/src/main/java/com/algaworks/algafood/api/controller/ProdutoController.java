package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.ProdutoInputDisassembler;
import com.algaworks.algafood.api.assembler.ProdutoModelAssembler;
import com.algaworks.algafood.api.model.ProdutoModel;
import com.algaworks.algafood.api.model.input.ProdutoInput;
import com.algaworks.algafood.domain.model.Produto;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.ProdutoRepository;
import com.algaworks.algafood.domain.service.CadastroProdutoService;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("restaurantes/{idRestaurante}/produtos")
public class ProdutoController {
	
	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private CadastroProdutoService cadastroProdutoService;
	
	@Autowired
	private ProdutoModelAssembler produtoModelAssembler;
	
	@Autowired
	private ProdutoInputDisassembler produtoInputDisassembler;
	
	@Autowired
	private ProdutoRepository produtoRepository;
	
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public ProdutoModel adicionar(@PathVariable Integer idRestaurante, 
			 @RequestBody @Valid ProdutoInput produtoInput) {
		Restaurante restaurante= cadastroRestauranteService.buscarOuFalhar(idRestaurante);
		Produto produto=produtoInputDisassembler.toDomainObject(produtoInput);
		produto.setRestaurante(restaurante);
		return produtoModelAssembler.toModel(cadastroProdutoService.salvar(produto));
	}		
	@PutMapping("/{idProduto}")
	public ProdutoModel atualizar(@PathVariable Integer idRestaurante, 
			@PathVariable Integer idProduto, @RequestBody @Valid ProdutoInput produtoInput) {
		Produto produto=cadastroProdutoService.buscarOuFalhar(idRestaurante,idProduto);
		produtoInputDisassembler.copyToDomainObject(produtoInput, produto);
		return produtoModelAssembler.toModel(cadastroProdutoService.salvar(produto)); 		
	}
	@GetMapping("/{idProduto}")
	public ProdutoModel buscar(@PathVariable Integer idRestaurante, 
			@PathVariable Integer idProduto) {
		Produto produto=cadastroProdutoService.buscarOuFalhar(idRestaurante, idProduto);
		return produtoModelAssembler.toModel(produto);		
	}
	@GetMapping
	public List<ProdutoModel> listar(@PathVariable Integer idRestaurante, @RequestParam(required = false) boolean incluirInativos ) {
		Restaurante restaurante= cadastroRestauranteService.buscarOuFalhar(idRestaurante);
		if(incluirInativos) {
			return produtoModelAssembler.toCollectionsModel(produtoRepository.findTodosByRestaurante(restaurante));
		}else {
			return produtoModelAssembler.toCollectionsModel(produtoRepository.findAtivosByRestaurante(restaurante));
		}
	}

}
