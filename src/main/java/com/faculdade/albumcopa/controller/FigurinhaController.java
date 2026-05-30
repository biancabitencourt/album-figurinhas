package com.faculdade.albumcopa.controller;

import com.faculdade.albumcopa.service.FigurinhaService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class FigurinhaController {
	
	 private final FigurinhaService figurinhaService;

	 public FigurinhaController(FigurinhaService figurinhaService) {
		 this.figurinhaService = figurinhaService;
	 }

	 @GetMapping("/figurinhas")
	 public String listar(Model model)	{
		 
		 model.addAttribute(
				 "figurinhas",
				 figurinhaService.listarTodas()
		);
		 
		 return "figurinhas";
	 }

}
