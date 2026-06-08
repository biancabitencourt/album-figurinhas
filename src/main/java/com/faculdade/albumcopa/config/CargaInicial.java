package com.faculdade.albumcopa.config;

import com.faculdade.albumcopa.model.Figurinha;
import com.faculdade.albumcopa.repository.FigurinhaRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class CargaInicial implements CommandLineRunner {
	
	private final FigurinhaRepository figurinhaRepository;
	
	public CargaInicial(FigurinhaRepository figurinhaRepository)	{
		this.figurinhaRepository = figurinhaRepository;
	}

@Override
public void run(String... args)	{
	if (figurinhaRepository.count() == 0)	{
		for (int i = 1; i <= 200; i++)	{
			figurinhaRepository.save(new Figurinha(i));
		}
	}
  }
}
