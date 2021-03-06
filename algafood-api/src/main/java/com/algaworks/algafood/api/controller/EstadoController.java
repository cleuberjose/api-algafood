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

import com.algaworks.algafood.api.assembler.EstadoInputDisassembler;
import com.algaworks.algafood.api.assembler.EstadoModelAssembler;
import com.algaworks.algafood.api.model.EstadoModel;
import com.algaworks.algafood.api.model.input.EstadoInput;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.EstadoRepository;
import com.algaworks.algafood.domain.service.CadastroEstadoService;

@RestController
@RequestMapping("/estados")
public class EstadoController {

	@Autowired	
	private EstadoRepository estadoRepository;

	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@Autowired
	private EstadoInputDisassembler estadoInputDisassembler;

	@Autowired
	private EstadoModelAssembler estadoModelAssembler;

	@GetMapping
	//	@ResponseStatus(HttpStatus.CREATED)
	public List<EstadoModel> listar(){
		return estadoModelAssembler.toCollectionModel(estadoRepository.findAll());
	}

	@GetMapping("/{estadoId}")
	//@PathVariable("estadoId") 
	public EstadoModel buscar(@PathVariable Integer estadoId){
		return estadoModelAssembler.toModel(
				cadastroEstadoService.buscarOuFalhar(estadoId));
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED )
	public EstadoModel adicionar(@RequestBody @Valid EstadoInput estadoInput) {
		Estado estado= cadastroEstadoService.salvar(
				estadoInputDisassembler.toDomainObject(estadoInput));
		return estadoModelAssembler.toModel(estado);
	}

	@PutMapping("/{idEstado}")
	public EstadoModel atualizar(@PathVariable Integer idEstado,
			@RequestBody @Valid EstadoInput estadoInput){
		Estado estadoAtual= cadastroEstadoService.buscarOuFalhar(idEstado);
		estadoInputDisassembler.copyToDomainObject(estadoInput, estadoAtual);
//		BeanUtils.copyProperties(estado, estadoAtual, "id");
		return estadoModelAssembler.toModel(cadastroEstadoService.salvar(estadoAtual));
	}	
	@DeleteMapping("/{idEstado}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer idEstado){
		cadastroEstadoService.remover(idEstado);
	}	
}
