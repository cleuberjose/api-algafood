package com.algaworks.algafood.api.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes/{idRestaurante}/formasPagamento")
public class RestauranteFormaPagamentoController {

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@GetMapping
	public List<FormaPagamentoModel> listar(@PathVariable Integer idRestaurante) {
		Restaurante restaurante= cadastroRestauranteService.buscarOuFalhar(idRestaurante);
		return formaPagamentoModelAssembler.toCollectionsModel(restaurante.getFormaPagamentos());
	}	
	@PutMapping("/{idFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarFormaPagamento(@PathVariable Integer idRestaurante, 
			@PathVariable Integer idFormaPagamento) {
		cadastroRestauranteService.associarFormaPagamento(idRestaurante, idFormaPagamento);
	}
	@DeleteMapping("/{idFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarFormaPagamento(@PathVariable Integer idRestaurante, 
			@PathVariable Integer idFormaPagamento) {
		cadastroRestauranteService.desassociarFormaPagamento(idRestaurante, idFormaPagamento);
	}
	
}
