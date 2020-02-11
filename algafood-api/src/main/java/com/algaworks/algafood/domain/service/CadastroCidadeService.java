package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.CidadeNaoEncontradaException;
import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.model.Cidade;
import com.algaworks.algafood.domain.model.Estado;
import com.algaworks.algafood.domain.repository.CidadeRepository;

@Service
public class CadastroCidadeService {
	
	private static final String MSG_CIDADE_EM_USO = "A cidade de código %d não pode ser removida, pois está em uso";

	@Autowired
	private CidadeRepository cidadeRepository;
	
	@Autowired
	private CadastroEstadoService cadastroEstadoService;
	
	@Transactional
	public Cidade salvar(Cidade cidade) {
		Estado estado= cadastroEstadoService.buscarOuFalhar(cidade.getEstado().getId());
		cidade.setEstado(estado);
		return cidadeRepository.save(cidade);
		
	}
	@Transactional
	public void remover(Integer id) {
		try {
			cidadeRepository.deleteById(id);
			cidadeRepository.flush();
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(
					String.format(MSG_CIDADE_EM_USO,id));
		}catch (EmptyResultDataAccessException e) {
			throw new CidadeNaoEncontradaException( id);
		}
	}
	public Cidade buscarOuFalhar(Integer idCidade) {
		return cidadeRepository.findById(idCidade)
				.orElseThrow(()->new CidadeNaoEncontradaException(idCidade));
	}
}
