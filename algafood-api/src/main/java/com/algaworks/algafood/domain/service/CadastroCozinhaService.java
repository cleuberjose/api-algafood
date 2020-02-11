package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.CozinhaNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cozinha;
import com.algaworks.algafood.domain.repository.CozinhaRepository;

@Service
public class CadastroCozinhaService {
	@Autowired
	private CozinhaRepository cozinhaRepository;

	@Transactional
	public Cozinha salvar(Cozinha cozinha) {
		return cozinhaRepository.save(cozinha);
	}
	@Transactional
	public void remover(Integer id) {
		try {
			cozinhaRepository.deleteById(id);
			cozinhaRepository.flush();
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format("A cozinha de código %d não pode ser removida, pois está em uso ",id));
		}catch (EmptyResultDataAccessException e) {
			throw new CozinhaNaoEncontradaException(id);
		}
	}
	public Cozinha buscarOuFalhar(Integer cozinhaId) {
		return cozinhaRepository.findById(cozinhaId).orElseThrow(()-> 
		new CozinhaNaoEncontradaException(cozinhaId));
	}
}
