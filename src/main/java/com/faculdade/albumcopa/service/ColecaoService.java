package com.faculdade.albumcopa.service;

import com.faculdade.albumcopa.model.*;
import com.faculdade.albumcopa.repository.*;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class ColecaoService {
	
	public static final int TOTAL_FIGURINHAS = 200;

	private final ColecaoFigurinhaRepository colecaoRepository;
	private final FigurinhaRepository figurinhaRepository;
	
	public ColecaoService(ColecaoFigurinhaRepository colecaoRepository, FigurinhaRepository figurinhaRepository) {
		this.colecaoRepository = colecaoRepository;
		this.figurinhaRepository = figurinhaRepository;
	}
	
	public void validarNumeroFigurinha(int numero)	{
		if (numero < 1 || numero > TOTAL_FIGURINHAS)	{
			throw new RuntimeException ("Numero invalido: " + numero + "Use apenas numeros entre 1 e 200");
		}
	}
	
	
	public List<Integer> converterEntrada(String entrada)	{
		if (entrada == null || entrada.isBlank())	{
			throw new RuntimeException("Nenhuma figurinha valida foi informada");
		}
		
		String limpa = entrada.replace(","," ").trim();
		String[] partes = limpa.split("\\s+");
	
		List<Integer> numeros = new ArrayList<>();
		for (String parte : partes)	{
			if (parte.isBlank())	{
				continue;
			}
			int numero;
			try {
				numero = Integer.parseInt(parte);
			}
			catch (NumberFormatException e)	{
				throw new RuntimeException("Entrada invalida: " + parte + ". Digite apenas números separados por espaço ou vírgula.");
			}
			validarNumeroFigurinha(numero);
			numeros.add(numero);
		}
	
		if (numeros.isEmpty())	{
			throw new RuntimeException("Nenhuma figurinha válida foi informada");
		}
		return numeros;
	}
	
	public int adicionarFigurinhas(Usuario usuario, String entrada)	{
		List<Integer> numeros = converterEntrada(entrada);
		for (int numero : numeros)	{
			adicionarFigurinha(usuario, numero);
		}
		return numeros.size();
	}
	
	public void adicionarFigurinha(Usuario usuario, int numero)	{
		Figurinha figurinha = figurinhaRepository.findByNumero(numero)
				.orElseThrow(() -> new RuntimeException("Figurinha não encontrada: " + numero));
		
		Optional<ColecaoFigurinha> existente = 
				colecaoRepository.findByUsuarioAndFigurinha(usuario, figurinha);
		
		if (existente.isPresent())	{
			ColecaoFigurinha item = existente.get();
			item.setQuantidade(item.getQuantidade() +1);
			colecaoRepository.save(item);
		}
		else	{
			colecaoRepository.save(new ColecaoFigurinha(usuario, figurinha, 1));
		}
	}
	
	public List<ColecaoFigurinha> listarDoUsuario(Usuario usuario)	{
		return colecaoRepository.findByUsuario(usuario);
	}
	
	public void removerFigurinha(Usuario usuario, int numero)	{
		Figurinha figurinha = figurinhaRepository.findByNumero(numero)
				.orElseThrow(() -> new RuntimeException("Figurinha não encontrada:" + numero));
		
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
	
	public List<Integer> listarFaltantes(Usuario usuario)	{
		
		List<Integer> possuidos = listarDoUsuario(usuario).stream()
				.map(item -> item.getFigurinha().getNumero()).toList();
		
		List<Integer> faltantes = new ArrayList<>();
		for (int n = 1; n <= TOTAL_FIGURINHAS; n++)	{
			if (!possuidos.contains(n))	{
				faltantes.add(n);
			}
		}
		return faltantes;
	}
	
	public List<ColecaoFigurinha> listarRepetidas(Usuario usuario)	{
		return listarDoUsuario(usuario).stream()
				.filter(item -> item.getQuantidade() > 1).toList();
	}
	
	public int quantidadeDistintas(Usuario usuario)	{
		return listarDoUsuario(usuario).size();
	}
	
	public int calcularProgresso(Usuario usuario) {
		int distintas = quantidadeDistintas(usuario);
		return (int) Math.round((distintas * 100.0) / TOTAL_FIGURINHAS);
	}
	
}	
	
	