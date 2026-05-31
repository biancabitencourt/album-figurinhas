package com.faculdade.albumcopa.controller;

import com.faculdade.albumcopa.model.Usuario;
import com.faculdade.albumcopa.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@Controller
public class AuthController {
	
	private final UsuarioService usuarioService;

	public AuthController(UsuarioService usuarioService) {
		super();
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/login")
	public String telaLogin()	{
		return "login";
	}
	
	@PostMapping("/login")
	public String fazerLogin(@RequestParam String login,
							 @RequestParam String senha,
							 HttpSession session, Model model)	{
    		
		Optional<Usuario> usuario = usuarioService.autenticar(login, senha);
		if (usuario.isPresent()) {
			session.setAttribute("UsuarioId", usuario.get().getId());
			session.setAttribute("usuarioLogin", usuario.get().getLogin());
			return "redirect:/figurinhas";
		}
		
		model.addAttribute("erro", "LOGIN OU SENHA INVALIDOS");
		return "login";
	}
		
	@GetMapping("/cadastro")	
	public String telaCadastro()	{
		return "cadastro";
	}
	
	@PostMapping("/cadastro")
	public String fazerCadastro(@RequestParam String login,
								@RequestParam String senha, Model model	)	{
		
		try {
			usuarioService.cadastrar(login,senha);
			return "redirect:/login";
		}	
		catch (RuntimeException e)	{
			model.addAttribute("erro", e.getMessage());
			return "cadastro";
		}	
	}
	
	@GetMapping("/logout")
	public String logout(HttpSession session)	{
		session.invalidate();
		return "redirect:/login";
	}

}