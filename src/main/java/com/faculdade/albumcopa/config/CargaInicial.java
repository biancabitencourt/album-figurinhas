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
		 figurinhaRepository.save(new Figurinha("Endrick", "Brasil", false));
		 figurinhaRepository.save(new Figurinha("Vinicius Jr", "Brasil", true));
		 figurinhaRepository.save(new Figurinha("Rodrygo", "Brasil", false));
		 figurinhaRepository.save(new Figurinha("Messi", "Argentina", true));
		 figurinhaRepository.save(new Figurinha("Julian Alvarez", "Argentina", false));
		 figurinhaRepository.save(new Figurinha("Mbappé", "França", true));
		 figurinhaRepository.save(new Figurinha("Griezmann", "França", false));
	}
  }
}
