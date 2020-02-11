package com.algaworks.algafood.domain.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.GrupoNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Permissao;
import com.algaworks.algafood.domain.repository.GrupoRepository;

@Service
public class CadastroGrupoService {
	private static final String MSG_GRUPO_EM_USO="O Grupo de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private GrupoRepository grupoRepository;
	
	@Autowired
	private CadastroPermissaoService cadastroPermissaoService;
	
	@Transactional
	public Grupo salvar(Grupo grupo) {
		return grupoRepository.save(grupo);
	}
	@Transactional
	public void remover(Integer id) {
		try {
			grupoRepository.deleteById(id);
			grupoRepository.flush();
		}catch (DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_GRUPO_EM_USO, id));
		}catch(EmptyResultDataAccessException e){
			throw new GrupoNaoEncontradoException(id);
		}
	}
	public Grupo buscarOuFalhar(Integer id) {
		return grupoRepository.findById(id).orElseThrow(()
				->new GrupoNaoEncontradoException(id));
	}
	@Transactional
	public void associarPermissao(Integer idGrupo, Integer idPermissao) {
		Grupo grupo= buscarOuFalhar(idGrupo);
		Permissao permissao=cadastroPermissaoService.buscarOuFalhar(idPermissao);
		grupo.adicionarPermissao(permissao);		
	}
	@Transactional
	public void desassociarPermissao(Integer idGrupo, Integer idPermissao) {
		Grupo grupo= buscarOuFalhar(idGrupo);
		Permissao permissao=cadastroPermissaoService.buscarOuFalhar(idPermissao);
		grupo.removerPermissao(permissao);		
	}

}
