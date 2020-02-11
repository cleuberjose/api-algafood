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

import com.algaworks.algafood.api.assembler.GrupoInputDisassembler;
import com.algaworks.algafood.api.assembler.GrupoModelAssembler;
import com.algaworks.algafood.api.model.GrupoModel;
import com.algaworks.algafood.api.model.input.GrupoInput;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.repository.GrupoRepository;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos")
public class GrupoController {
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private GrupoModelAssembler grupoModelAssembler;
	
	@Autowired
	private GrupoInputDisassembler grupoInputDisassembler;
	
	@GetMapping
	public List<GrupoModel> listar(){
		return grupoModelAssembler.toCollectionsModel(grupoRepository.findAll());
	}
	@GetMapping("/{idGrupo}")
	public GrupoModel buscar(@PathVariable Integer idGrupo) {
		return grupoModelAssembler.toModel(cadastroGrupoService.buscarOuFalhar(idGrupo));
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public GrupoModel adicionar(@RequestBody @Valid GrupoInput grupoInput) {
		Grupo grupo=cadastroGrupoService.salvar(grupoInputDisassembler.toDomainObject(grupoInput));
		return grupoModelAssembler.toModel(grupo);
	}
	@PutMapping("/{idGrupo}")
	public GrupoModel atualizar(@RequestBody @Valid GrupoInput grupoInput, @PathVariable Integer idGrupo) {
		Grupo grupo=cadastroGrupoService.buscarOuFalhar(idGrupo);
		grupoInputDisassembler.copyToDomainObject(grupoInput, grupo);
		cadastroGrupoService.salvar(grupo);
		return grupoModelAssembler.toModel(grupo);
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		cadastroGrupoService.remover(id);
	}
	

}
