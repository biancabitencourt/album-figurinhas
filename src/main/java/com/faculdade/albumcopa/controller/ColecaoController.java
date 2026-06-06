package com.faculdade.albumcopa.controller;

import com.faculdade.albumcopa.model.Usuario;
import com.faculdade.albumcopa.service.ColecaoService;
import com.faculdade.albumcopa.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
public class ColecaoController {

	private final ColecaoService colecaoService;
	private final UsuarioService usuarioService;
	
	public ColecaoController(ColecaoService colecaoService, UsuarioService usuarioService) {
		this.colecaoService = colecaoService;
		this.usuarioService = usuarioService;
	}
	
	@PostMapping("/colecao/adicionar")
	public String adicionar(@RequestParam Long figurinhaId, HttpSession session)	{
	
	Long usuarioId = (Long) session.getAttribute("UsuarioId");
	
	if (usuarioId == null)	
		return "redirect:/login";
		
	Usuario usuario = usuarioService.buscarPorId(usuarioId);
	colecaoService.adicionar(usuario, figurinhaId);
	return "redirect:/figurinhas";
	
	}

}
