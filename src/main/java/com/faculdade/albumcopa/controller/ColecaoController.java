package com.faculdade.albumcopa.controller;

import com.faculdade.albumcopa.model.Usuario;
import com.faculdade.albumcopa.service.ColecaoService;
import com.faculdade.albumcopa.service.UsuarioService;
import jakarta.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.ui.Model;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

@Controller
public class ColecaoController {

	private final ColecaoService colecaoService;
	private final UsuarioService usuarioService;
	
	public ColecaoController(ColecaoService colecaoService, UsuarioService usuarioService) {
		this.colecaoService = colecaoService;
		this.usuarioService = usuarioService;
	}
	
	@GetMapping("/minha-colecao")
	public String minhaColecao(HttpSession session, Model model)	{
		Long usuarioId = (Long) session.getAttribute("UsuarioId");
		if (usuarioId == null) return "redirect:/login";
		
		Usuario usuario = usuarioService.buscarPorId(usuarioId);
		
		model.addAttribute("colecao", colecaoService.listarDoUsuario(usuario));
		model.addAttribute("faltantes", colecaoService.listarFaltantes(usuario));
		model.addAttribute("repetidas", colecaoService.listarRepetidas(usuario));
		model.addAttribute("possuidas", colecaoService.quantidadeDistintas(usuario));
		model.addAttribute("total", ColecaoService.TOTAL_FIGURINHAS);
		model.addAttribute("progresso", colecaoService.calcularProgresso(usuario));
				
		return "minha-colecao";
 	}
	
	@GetMapping("/adicionar-figurinha")
	public String telaAdicionar(HttpSession session)	{
		if (session.getAttribute("UsuarioId") == null)
			return "redirect:/login";
		return "adicionar-figurinha";
	}
	
	
	@PostMapping("/adicionar-figurinha")
	public String adicionar(@RequestParam String entrada, 
			HttpSession session, Model model, RedirectAttributes redirect)	{
		Long usuarioId = (Long) session.getAttribute("UsuarioId");
		if (usuarioId == null)
			return "redirect:/login";
		Usuario usuario = usuarioService.buscarPorId(usuarioId);
		try	{
			int quantos = colecaoService.adicionarFigurinhas(usuario, entrada);
			redirect.addFlashAttribute("sucesso", quantos + "figurinhas adicionadas com sucesso!");
			return "redirect:/minha-colecao";
		}
		catch (RuntimeException e)	{
			model.addAttribute("erro", e.getMessage());
			model.addAttribute("entrada", entrada);
			return "adicionar-figurinha";
		}
				
	}
	
	@PostMapping("/colecao/remover")
	public String remover(@RequestParam int numero, HttpSession session)	{
		Long usuarioId = (Long) session.getAttribute("UsuarioId");
		if (usuarioId == null) 
			return "redirect:/login";
		
		Usuario usuario = usuarioService.buscarPorId(usuarioId);
		colecaoService.removerFigurinha(usuario, numero);
		return "redirect:/minha-colecao";
			
	}
	
}