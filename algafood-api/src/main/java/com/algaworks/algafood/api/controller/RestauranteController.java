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

import com.algaworks.algafood.api.assembler.RestauranteInputDisassembler;
import com.algaworks.algafood.api.assembler.RestauranteModelAssembler;
import com.algaworks.algafood.api.model.RestauranteModel;
import com.algaworks.algafood.api.model.input.RestauranteInput;
import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.repository.RestauranteRepository;
import com.algaworks.algafood.domain.service.CadastroRestauranteService;

@RestController
@RequestMapping("/restaurantes")
public class RestauranteController {

	@Autowired	
	private RestauranteRepository restauranteRepository;

	@Autowired
	private CadastroRestauranteService cadastroRestauranteService;
	
//	@Autowired
//	private SmartValidator validator;
	
	@Autowired
	private RestauranteModelAssembler restauranteModelAssembler;
	
	@Autowired
	private RestauranteInputDisassembler restauranteInputDisassembler;


//	@GetMapping
//	public List<Restaurante> listar(){
//		return restauranteRepository.findAll();
//	}
	@GetMapping
	public List<RestauranteModel> listar() {
		return restauranteModelAssembler.toCollectionModel(restauranteRepository.findAll());
	}

//	@GetMapping("/{restauranteId}")
//	public Restaurante buscar(@PathVariable Integer restauranteId){		
//		return cadastroRestauranteService.buscarOuFalhar(restauranteId);
//	}
	@GetMapping("/{restauranteId}")
	public RestauranteModel buscar(@PathVariable Integer restauranteId) {
		Restaurante restaurante = cadastroRestauranteService.buscarOuFalhar(restauranteId);
		
		return restauranteModelAssembler.toModel(restaurante);
	}
	
//	@PostMapping
//	@ResponseStatus(HttpStatus.CREATED)
//	public Restaurante adicionar(@RequestBody @Valid Restaurante restaurante) {
//		try {
//			return cadastroRestauranteService.salvar(restaurante);
//		}catch (CozinhaNaoEncontradaException e) {
//			throw new NegocioException(e.getMessage());
//		}
//	}
	@PostMapping
	@ResponseStatus(HttpStatus.CREATED)
	public RestauranteModel adicionar(@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			
			return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restaurante));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}

//	@PutMapping("/{idRestaurante}")
//	public Restaurante atualizar(@PathVariable Integer idRestaurante,
//			@RequestBody @Valid Restaurante restaurante){
//		Restaurante restauranteAtual=cadastroRestauranteService.buscarOuFalhar(idRestaurante);
//		BeanUtils.copyProperties(restaurante, restauranteAtual, "id","formaPagamentos","endereco",
//				"dataCadastro","produtos");
//		try {
//			return cadastroRestauranteService.salvar(restauranteAtual);
//		}catch (CozinhaNaoEncontradaException e) {
//			throw new NegocioException(e.getMessage());
//		}
//	}	s
	@PutMapping("/{restauranteId}")
	public RestauranteModel atualizar(@PathVariable Integer restauranteId,
			@RequestBody @Valid RestauranteInput restauranteInput) {
		try {
//			Restaurante restaurante = restauranteInputDisassembler.toDomainObject(restauranteInput);
			
			Restaurante restauranteAtual = cadastroRestauranteService.buscarOuFalhar(restauranteId);
			
			restauranteInputDisassembler.copyToDomainObject(restauranteInput, restauranteAtual);
			
//			BeanUtils.copyProperties(restaurante, restauranteAtual, 
//					"id", "formasPagamento", "endereco", "dataCadastro", "produtos");

			return restauranteModelAssembler.toModel(cadastroRestauranteService.salvar(restauranteAtual));
		} catch (CozinhaNaoEncontradaException | CidadeNaoEncontradaException e) {
			throw new NegocioException(e.getMessage());
		}
	}
//	@PatchMapping("/{idRestaurante}")
//	public Restaurante atualizar(@PathVariable Integer idRestaurante,
//			@RequestBody Map<String,Object> campos, HttpServletRequest request){
//		Restaurante restauranteAtual=cadastroRestauranteService.buscarOuFalhar(idRestaurante);
//		merge(campos, restauranteAtual,request);
//		validade(restauranteAtual,"restaurante");
//		atualizar(idRestaurante, restauranteAtual);
//		return restauranteAtual;
//
//	}
//
//	private void validade(Restaurante restaurante, String objectName) {
//		BeanPropertyBindingResult bindingResult= new BeanPropertyBindingResult(restaurante, objectName);
//		validator.validate(restaurante, bindingResult);
//		if(bindingResult.hasErrors()) {
//			throw new ValidacaoException(bindingResult);
//		}
//		
//	}
//
//	private void merge(Map<String, Object> campos, Restaurante restaurante, HttpServletRequest request) {
//		ServletServerHttpRequest  serverHttpRequest= new ServletServerHttpRequest(request);
//		try {
//			ObjectMapper objectMapper= new ObjectMapper();
//			objectMapper.configure(DeserializationFeature.FAIL_ON_IGNORED_PROPERTIES,true);
//			objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,true);
//			Restaurante restauranteAnterior= objectMapper.convertValue(campos, Restaurante.class);
//			campos.forEach((chave, valor) -> {
//				Field field= ReflectionUtils.findField(Restaurante.class, chave);
//				field.setAccessible(true);
//	
//				Object novoValor= ReflectionUtils.getField(field, restauranteAnterior);
//	
//				ReflectionUtils.setField(field, restaurante, novoValor);
//			});
//		}catch(IllegalArgumentException e) {
//			Throwable rootCause=ExceptionUtils.getRootCause(e);
//			throw new HttpMessageNotReadableException(e.getMessage(),rootCause, serverHttpRequest);
//		}
//	}	
	@DeleteMapping("/{idRestaurante}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void remover(@PathVariable Integer idRestaurante){
		cadastroRestauranteService.remover(idRestaurante);
	}	
//	@GetMapping("/por-nome")
//	public List<Restaurante> pesquisarPorNome(String nome, Integer idCozinha){
//		return restauranteRepository.pesquisarPorNomeCozinha(nome, idCozinha);
//	}
//	@GetMapping("nome-entre-valores")
//	public List<Restaurante> pesquisarEntreTaxaEntrega(String nome, BigDecimal valorInicio, BigDecimal valorFim){
//		return restauranteRepository.find(nome, valorInicio, valorFim);		
//	}
//	@GetMapping("nome-frete-gratis")
//	public List<Restaurante> pesquisarNomeFreteGratis(String nome){
//		//		RestauranteFreteGratisSpec restauranteFreteGratisSpec= new RestauranteFreteGratisSpec();
//		//		RestauranteNomeSemelhanteSpec restauranteNomeSemelhanteSpec= new RestauranteNomeSemelhanteSpec(nome);
//		return restauranteRepository.pesquisarNomeFreteGratis(nome);
//
//	}
//	@GetMapping("primeiro")
//	public Optional<Restaurante> buscarPrimeiro(){
//		return restauranteRepository.buscarPrimeiro();
//
//	}
	@PutMapping("/{restauranteId}/ativo")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void ativar(@PathVariable Integer restauranteId) {
		cadastroRestauranteService.ativar(restauranteId);
	}
	
	@DeleteMapping("/{restauranteId}/ativo")
	@ResponseStatus(code = HttpStatus.NO_CONTENT)
	public void inativar(@PathVariable Integer restauranteId) {
		cadastroRestauranteService.inativar(restauranteId);
	}
	@PutMapping("/{restauranteId}/abertura")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void abrir(@PathVariable Integer restauranteId) {
		cadastroRestauranteService.abrir(restauranteId);
	}
	@PutMapping("/{restauranteId}/fechamento")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void fechar(@PathVariable Integer restauranteId) {
		cadastroRestauranteService.fechar(restauranteId);
	}
	@PutMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void ativar(@RequestBody List<Integer> idRestaurantes) {
		cadastroRestauranteService.ativar(idRestaurantes);
	}
	@DeleteMapping("/ativacoes")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void inaativarMassa(@RequestBody List<Integer> idRestaurantes) {
		cadastroRestauranteService.inativar(idRestaurantes);
	}
	
}
