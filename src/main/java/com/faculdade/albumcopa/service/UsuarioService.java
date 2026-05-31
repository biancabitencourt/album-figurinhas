package com.faculdade.albumcopa.service;

import com.faculdade.albumcopa.model.Usuario;
import com.faculdade.albumcopa.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import java.util.Optional;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;;
	
	public UsuarioService(UsuarioRepository usuarioRepository)	{
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario cadastrar(String login, String senha)	{
		if(usuarioRepository.findByLogin(login).isPresent())	{
			throw new RuntimeException("Login ja existe!");
		}
		return usuarioRepository.save(new Usuario(login,senha));
	}
	
	public Optional<Usuario> autenticar(String login, String senha)	{
		return usuarioRepository.findByLogin(login)
				.filter(u -> u.getSenha().equals(senha));
	}
 
}
