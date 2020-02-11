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

import com.algaworks.algafood.api.assembler.PermissaoModelAssembler;
import com.algaworks.algafood.api.model.input.PermissaoModel;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.service.CadastroGrupoService;

@RestController
@RequestMapping("/grupos/{idGrupo}/permissoes")
public class GrupoPermissaoController {
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	
	@Autowired
	private PermissaoModelAssembler permissaoModelAssembler;
	
	@GetMapping
	public List<PermissaoModel> listar(@PathVariable Integer idGrupo){
		Grupo grupo=cadastroGrupoService.buscarOuFalhar(idGrupo);
		return permissaoModelAssembler.toCollectionsModel(grupo.getPermissoes());
	}

	@PutMapping("/{idPermissao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void associarPermissao(@PathVariable Integer idGrupo, 
			@PathVariable Integer idPermissao) {
		cadastroGrupoService.associarPermissao(idGrupo, idPermissao);
	}
	@DeleteMapping("/{idPermissao}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void desassociarPermissao(@PathVariable Integer idGrupo, 
			@PathVariable Integer idPermissao) {
		cadastroGrupoService.desassociarPermissao(idGrupo, idPermissao);
	}
}
