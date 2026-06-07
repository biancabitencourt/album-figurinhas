package com.faculdade.albumcopa.service;

import com.faculdade.albumcopa.model.*;
import com.faculdade.albumcopa.repository.*;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.Optional;

@Service
public class ColecaoService {

	private final ColecaoFigurinhaRepository colecaoRepository;
	private final FigurinhaRepository figurinhaRepository;
	
	public ColecaoService(ColecaoFigurinhaRepository colecaoRepository, FigurinhaRepository figurinhaRepository) {
		this.colecaoRepository = colecaoRepository;
		this.figurinhaRepository = figurinhaRepository;
	}
	
	public void adicionar(Usuario usuario, Long figurinhaId)	{
		Figurinha figurinha = figurinhaRepository.findById(figurinhaId).orElseThrow (
				() -> new RuntimeException("Figurinha não encontrada"));		
		
		Optional<ColecaoFigurinha> existente =
			colecaoRepository.findByUsuarioAndFigurinha(usuario, figurinha);
	
		if(existente.isPresent())	{
			ColecaoFigurinha item = existente.get();
			item.setQuantidade(item.getQuantidade() + 1);
			colecaoRepository.save(item);
		}
		else {
			colecaoRepository.save(new ColecaoFigurinha(usuario, figurinha, 1));
		}	
	}
	
	public List<ColecaoFigurinha> listaDoUsuario(Usuario usuario)	{
		return colecaoRepository.findByUsuario(usuario);
	}
	
	public void remover(Usuario usuario, Long figurinhaId)	{
		Figurinha figurinha = figurinhaRepository.findById(figurinhaId).orElseThrow(
				() -> new RuntimeException("Figurinha não encontrada"));
		
		colecaoRepository.findByUsuarioAndFigurinha(usuario, figurinha).ifPresent(item ->	{
			int nova = item.getQuantidade() - 1;
			if (nova <= 0)	{
				colecaoRepository.delete(item);
			}
			else	{
				item.setQuantidade(nova);
				colecaoRepository.save(item);
			}
		});
	}
}
