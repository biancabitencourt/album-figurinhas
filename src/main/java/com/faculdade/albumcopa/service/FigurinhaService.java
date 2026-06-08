package com.faculdade.albumcopa.service;

import com.faculdade.albumcopa.model.Figurinha;
import com.faculdade.albumcopa.repository.FigurinhaRepository;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class FigurinhaService {

	private final FigurinhaRepository figurinhaRepository;
	
	public FigurinhaService(FigurinhaRepository figurinhaRepository)	{
		this.figurinhaRepository = figurinhaRepository;
	}
	
	public List<Figurinha> listarTodas()	{
		return figurinhaRepository.findAllByOrderByNumeroAsc();
	}
}
