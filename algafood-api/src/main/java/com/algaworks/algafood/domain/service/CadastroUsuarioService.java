package com.algaworks.algafood.domain.service;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.algaworks.algafood.domain.exception.EntidadeEmUsoException;
import com.algaworks.algafood.domain.exception.NegocioException;
import com.algaworks.algafood.domain.exception.UsuarioNaoEncontradoException;
import com.algaworks.algafood.domain.model.Grupo;
import com.algaworks.algafood.domain.model.Usuario;
import com.algaworks.algafood.domain.repository.UsuarioRepository;

@Service
public class CadastroUsuarioService {
	
	private static final String MSG_USUARIO_EM_USO = "O usuario de código %d não pode ser removido, pois está em uso";
	
	@Autowired
	private CadastroGrupoService cadastroGrupoService;
	
	@Autowired
	private UsuarioRepository usuarioRepository;
	
	@Transactional
	public Usuario salvar(Usuario usuario) {
		usuarioRepository.detach(usuario);
		Optional<Usuario> usuarioCadastrado=usuarioRepository.findByEmail(usuario.getEmail());
		if(usuarioCadastrado.isPresent() && !usuarioCadastrado.get().equals(usuario)) {
			throw new NegocioException(
					String.format("Já existe um usuário cadastrado com o email %s", usuario.getEmail()));
		}
		return usuarioRepository.save(usuario); 
	}
	
	public Usuario buscarOuFalhar(Integer id) {
		return usuarioRepository.findById(id).orElseThrow(()-> 
			new UsuarioNaoEncontradoException(id));
	}
	
	@Transactional
	public void remover(Integer id) {
		try {
			usuarioRepository.deleteById(id);
		}catch(EmptyResultDataAccessException e) {
			throw new UsuarioNaoEncontradoException(id);
		}catch(DataIntegrityViolationException e) {
			throw new EntidadeEmUsoException(String.format(MSG_USUARIO_EM_USO, id));
		}
	}
	@Transactional
	public void atualizarSenha(Integer id, String senha, String novaSenha) {
		Usuario usuario=buscarOuFalhar(id);
		if(usuario.senhaNaoCoincideCom(senha)) {
			throw new NegocioException("Senha atual informada não coincide com a senha do usuário");
		}
		usuario.setSenha(novaSenha);
		
	}
	@Transactional
	public void associarGrupo(Integer idUsuario, Integer idGrupo) {
		Usuario usuario= buscarOuFalhar(idUsuario);
		Grupo grupo=cadastroGrupoService.buscarOuFalhar(idGrupo);
		usuario.adicionarGrupo(grupo);
		
	}
	@Transactional
	public void desassociarGrupo(Integer idUsuario, Integer idGrupo) {
		Usuario usuario= buscarOuFalhar(idUsuario);
		Grupo grupo=cadastroGrupoService.buscarOuFalhar(idGrupo);
		usuario.removerGrupo(grupo);
		
	}

}
