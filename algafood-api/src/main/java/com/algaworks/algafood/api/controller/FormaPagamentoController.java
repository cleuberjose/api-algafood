package com.algaworks.algafood.api.controller;

import java.util.List;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.FormaPagamentoInputDisassembler;
import com.algaworks.algafood.api.assembler.FormaPagamentoModelAssembler;
import com.algaworks.algafood.api.model.FormaPagamentoModel;
import com.algaworks.algafood.api.model.input.FormaPagamentoInput;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.repository.FormaPagamentoRepository;
import com.algaworks.algafood.domain.service.CadastroFormaPagamentoService;

@RestController
@RequestMapping("/formaPagamentos")
public class FormaPagamentoController {
	
	@Autowired
	private FormaPagamentoRepository formaPagamentoRepository;
	
	@Autowired
	private CadastroFormaPagamentoService formaPagamentoService;
	
	@Autowired
	private FormaPagamentoModelAssembler formaPagamentoModelAssembler;
	
	@Autowired
	private FormaPagamentoInputDisassembler formaPagamentoInputDisassembler;
	
	@GetMapping
	public List<FormaPagamentoModel> listar(){
		return formaPagamentoModelAssembler.toCollectionsModel(formaPagamentoRepository.findAll());
	}
	@GetMapping("/{idFormaPagamento}")
	public FormaPagamentoModel buscar(@PathVariable Integer idFormaPagamento) {
		return formaPagamentoModelAssembler.toModel(formaPagamentoService.
				buscarOuFalhar(idFormaPagamento));
	}
	@PostMapping
	@ResponseStatus(code = HttpStatus.CREATED)
	public FormaPagamentoModel salvar(@RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamento=formaPagamentoService.salvar(
				formaPagamentoInputDisassembler.toDomainObject(formaPagamentoInput));
		return formaPagamentoModelAssembler.toModel(formaPagamento);
	}
	@PutMapping("/{idFormaPagamento}")
	public FormaPagamentoModel atualizar(@PathVariable Integer idFormaPagamento, @RequestBody @Valid FormaPagamentoInput formaPagamentoInput) {
		FormaPagamento formaPagamentoAtual= formaPagamentoService.buscarOuFalhar(idFormaPagamento);
		formaPagamentoInputDisassembler.copyToDomainObject(formaPagamentoInput, formaPagamentoAtual);
		return formaPagamentoModelAssembler.toModel(formaPagamentoService.salvar(formaPagamentoAtual));
	}
	@DeleteMapping("/{idFormaPagamento}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer idFormaPagamento) {
		formaPagamentoService.remover(idFormaPagamento);
	}
	
}
