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
	
/*	public Optional<Usuario> autenticar(String login, String senha)	{
		return usuarioRepository.findByLogin(login)
				.filter(u -> encoder.matches(senha, u.getSenha()));
	}
*/
	
	public Optional<Usuario> autenticar(String login, String senha) {

	    Optional<Usuario> usuario = usuarioRepository.findByLogin(login);

	    System.out.println("Login digitado: " + login);

	    if(usuario.isPresent()) {

	        System.out.println("Usuário encontrado");
	        System.out.println("Hash banco: " + usuario.get().getSenha());

	        boolean ok = encoder.matches(
	                senha,
	                usuario.get().getSenha());

	        System.out.println("Senha válida? " + ok);

	        if(ok) {
	            return usuario;
	        }

	    } else {

	        System.out.println("Usuário NÃO encontrado");

	    }

	    return Optional.empty();
	}
}
