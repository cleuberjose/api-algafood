package com.algaworks.algafood.domain.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.RestauranteNaoEncontradoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.model.FormaPagamento;
import com.algaworks.algafood.domain.model.Restaurante;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.RestauranteRepository;

@Service
public class CadastroRestauranteService {
	
	private static final String MSG_RESTAURANTE_EM_USO = "A restaurante de código %d não pode ser removido, pois está em uso";

	@Autowired
	private RestauranteRepository restauranteRepository;
		
	@Autowired
	private CadastroCozinhaService cadastroCozinhaService;
	
	@Autowired
	private CadastroCidadeService cadastroCidadeService;
	
	@Autowired
	private CadastroUsuarioService cadastroUsuarioService;
	
	@Autowired
	private CadastroFormaPagamentoService cadastroFormaPagamentoService;
	
	@Transactional
	public Restaurante salvar(Restaurante restaurante) {
		Cozinha cozinha= cadastroCozinhaService.buscarOuFalhar(restaurante.getCozinha().getId());		
		restaurante.setCozinha(cozinha);
		Cidade cidade=cadastroCidadeService.buscarOuFalhar(restaurante.getEndereco().getCidade().getId());	
		restaurante.getEndereco().setCidade(cidade);
		return restauranteRepository.save(restaurante);
	}
	
	@Transactional
	public void ativar(Integer idRestaurante) {
		Restaurante restaurante=buscarOuFalhar(idRestaurante);
		restaurante.ativar();
	}
	@Transactional
	public void ativar(List<Integer> idRestaurantes) {
		idRestaurantes.forEach(this::ativar);		
	}
	
	@Transactional
	public void inativar(Integer idRestaurante) {
		Restaurante restaurante=buscarOuFalhar(idRestaurante);
		restaurante.inativar();
	}
	@Transactional
	public void inativar(List<Integer> idRestaurantes) {
		idRestaurantes.forEach(this::inativar);		
	}
	
	@Transactional
	public void remover(Integer id) {
		try {
			restauranteRepository.deleteById(id);
			restauranteRepository.flush();
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_RESTAURANTE_EM_USO,id));
		}catch (EmptyResultDataAccessException e) {
			throw new RestauranteNaoEncontradoException( id);
		}
	}
	public Restaurante buscarOuFalhar(Integer idRestaurante) {
		return restauranteRepository.findById(idRestaurante).orElseThrow(()->
				new RestauranteNaoEncontradoException( idRestaurante));
	}
	@Transactional
	public void desassociarFormaPagamento(Integer idRestaurante, Integer idFormaPagamento) {
		Restaurante restaurante=buscarOuFalhar(idRestaurante);
		FormaPagamento formaPagamento=cadastroFormaPagamentoService.buscarOuFalhar(idFormaPagamento);
		restaurante.removerFormaPagamento(formaPagamento);		
	}
	@Transactional
	public void associarFormaPagamento(Integer idRestaurante, Integer idFormaPagamento) {
		Restaurante restaurante=buscarOuFalhar(idRestaurante);
		FormaPagamento formaPagamento=cadastroFormaPagamentoService.buscarOuFalhar(idFormaPagamento);
		restaurante.adicionarFormaPagamento(formaPagamento);		
	}
	@Transactional
	public void desassociarUsuario(Integer idRestaurante, Integer idUsuario) {
		Restaurante restaurante=buscarOuFalhar(idRestaurante);
		Usuario usuario=cadastroUsuarioService.buscarOuFalhar(idUsuario);
		restaurante.removerUsuario(usuario);		
	}
	@Transactional
	public void associarUsuario(Integer idRestaurante, Integer idUsuario) {
		Restaurante restaurante=buscarOuFalhar(idRestaurante);
		Usuario usuario=cadastroUsuarioService.buscarOuFalhar(idUsuario);
		restaurante.adicionarUsuario(usuario);		
	}

	@Transactional
	public void abrir(Integer idRestaurante) {
		Restaurante restaurante=buscarOuFalhar(idRestaurante);
		restaurante.abrir();		
	}
	@Transactional
	public void fechar(Integer idRestaurante) {
		Restaurante restaurante=buscarOuFalhar(idRestaurante);
		restaurante.fechar();		
	}

	

	

	
	
}
