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

import com.algaworks.algafood.api.assembler.UsuarioInputDisassembler;
import com.algaworks.algafood.api.assembler.UsuarioModelAssembler;
import com.algaworks.algafood.api.model.UsuarioModel;
import com.algaworks.algafood.api.model.input.UsuarioInput;
import com.algaworks.algafood.api.model.input.UsuarioNovaSenhaInput;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;
import com.algaworks.algafood.domain.service.CadastroUsuarioService;

@RestController
@RequestMapping("usuarios")
public class UsuarioController {
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Autowired
	private UsuarioInputDisassembler usuarioInputDisassembler;
		
	@Autowired
	private UsuarioModelAssembler usuarioModelAssembler;
		
	@GetMapping("/{id}")
	public UsuarioModel buscarPorId(@PathVariable Integer id) {
		return usuarioModelAssembler.toModel(cadastroUsuarioService.buscarOuFalhar(id));
	}
	@GetMapping
	public List<UsuarioModel> listar(){
		return usuarioModelAssembler.toCollectionsModel(usuarioRepository.findAll());
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public UsuarioModel adicionar(@RequestBody @Valid UsuarioInput usuarioInput) {
		Usuario usuario=cadastroUsuarioService.salvar(usuarioInputDisassembler.toDomainObject(usuarioInput));
		return usuarioModelAssembler.toModel(usuario);
	}
	@PutMapping("/{id}")
	public UsuarioModel atualizar(@RequestBody @Valid UsuarioInput usuarioInput, @PathVariable Integer id) {
		Usuario usuario=cadastroUsuarioService.buscarOuFalhar(id);
		usuarioInputDisassembler.cotytoDomainObject(usuarioInput, usuario);
		return usuarioModelAssembler.toModel(cadastroUsuarioService.salvar(usuario));
	}
	@DeleteMapping("/{id}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer id) {
		cadastroUsuarioService.remover(id);
		
	}
	@PutMapping("/{id}/novaSenha")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void atualizarSenha(@RequestBody @Valid UsuarioNovaSenhaInput usuarioNovaSenhaInput, @PathVariable Integer id ) {
		cadastroUsuarioService.atualizarSenha(id, usuarioNovaSenhaInput.getSenha(), usuarioNovaSenhaInput.getNovaSenha());
			
	}
}
