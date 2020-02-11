package com.algaworks.algafood.api.controller;

import java.util.List;
import java.util.Optional;

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
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.algaworks.algafood.api.assembler.CozinhaInputDisassembler;
import com.algaworks.algafood.api.assembler.CozinhaModelAssembler;
import com.algaworks.algafood.api.model.CozinhaModel;
import com.algaworks.algafood.api.model.input.CozinhaInput;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;
import com.algaworks.algafood.domain.service.CadastroCozinhaService;

@RestController
@RequestMapping("/cozinhas")
public class CozinhaController {

	@Autowired	
	private CozinhaRepository cozinhaRepository;

	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CozinhaInputDisassembler cozinhaInputDisassembler;

	@Autowired
	private CozinhaModelAssembler cozinhaModelAssembler;

	@GetMapping
	//	@ResponseStatus(HttpStatus.CREATED)
	public List<CozinhaModel> listar(){
		return cozinhaModelAssembler.toCollectionsModel(cozinhaRepository.findAll());
	}

	@GetMapping("/{cozinhaId}")
	//@PathVariable("cozinhaId") 
	public CozinhaModel buscar(@PathVariable Integer cozinhaId){
		return cozinhaModelAssembler.toModel(
				cadastroCozinhaService.buscarOuFalhar(cozinhaId));
	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED )
	public CozinhaModel adicionar(@RequestBody @Valid CozinhaInput cozinhaInput) {
		Cozinha cozinha= cadastroCozinhaService.salvar(
				cozinhaInputDisassembler.toDomainObject(cozinhaInput));
		return cozinhaModelAssembler.toModel(cozinha);
	}

	@PutMapping("/{idCozinha}")
	public CozinhaModel atualizar(@PathVariable Integer idCozinha,
			@RequestBody @Valid CozinhaInput cozinhaInput){
		Cozinha cozinhaAtual= cadastroCozinhaService.buscarOuFalhar(idCozinha);
//		BeanUtils.copyProperties(cozinha, cozinhaAtual, "id");
		cozinhaInputDisassembler.copyToDomainObject(cozinhaInput, cozinhaAtual);
		return cozinhaModelAssembler.toModel(cadastroCozinhaService.salvar(cozinhaAtual));
	}	
//	@DeleteMapping("/{idCozinha}")
//	public ResponseEntity<?> remover(@PathVariable Integer idCozinha){
//		try {
//			cadastroCozinhaService.remover(idCozinha);
//			return ResponseEntity.noContent().build();//204
//		}catch(EntidadeEmUsoException e) {
//			return ResponseEntity.status(HttpStatus.CONFLICT).body(e.getMessage());//409
//		}
////		catch (EntidadeNaoEncontradaException e) {
////			return ResponseEntity.notFound().build();
////		}
//	}		
	@DeleteMapping("/{idCozinha}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer idCozinha){
		cadastroCozinhaService.remover(idCozinha);
	}	
	@GetMapping("/cozinhas/por-nome")
	public List<CozinhaModel> pesquisarPorNome(@RequestParam("nome") String nome ){
		return cozinhaModelAssembler.toCollectionsModel(
				cozinhaRepository.findByNomeContainingIgnoreCase(nome));
	}
	@GetMapping("/cozinhas/por-nome-unico")
	public Optional<Cozinha> pesquisarPorNomeUnico(@RequestParam("nome") String nome ){
		return cozinhaRepository.findFirstByNomeContainingIgnoreCase(nome);
	}
	
	
}
