package com.faculdade.albumcopa.service;

import com.faculdade.albumcopa.model.Usuario;
import com.faculdade.albumcopa.repository.UsuarioRepository;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import java.util.Optional;

@Service
public class UsuarioService {
	
	private final UsuarioRepository usuarioRepository;
	private final BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
	
	public UsuarioService(UsuarioRepository usuarioRepository)	{
		this.usuarioRepository = usuarioRepository;
	}
	
	public Usuario cadastrar(String login, String senha)	{
		if(usuarioRepository.findByLogin(login).isPresent())	{
			throw new RuntimeException("Login ja existe!");
		}
		String senhaHash = encoder.encode(senha);
		return usuarioRepository.save(new Usuario(login,senhaHash));
	}
	
	public Optional<Usuario> autenticar(String login, String senha)	{
		return usuarioRepository.findByLogin(login)
				.filter(u -> encoder.matches(senha, u.getSenha()));
	}
	
	public Usuario buscarPorId(Long id)	{
		return usuarioRepository.findById(id).orElseThrow	(
			() -> new RuntimeException("Usuario nã encontrado"));
	}
}	
	

	
	