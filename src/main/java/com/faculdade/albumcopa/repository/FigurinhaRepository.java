package com.faculdade.albumcopa.repository;

import com.faculdade.albumcopa.model.Figurinha;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;


public interface FigurinhaRepository extends JpaRepository<Figurinha, Long> {
	
	Optional<Figurinha> findByNumero(Integer numero);
	
	boolean existsByNumero(Integer numero);
	
	List<Figurinha> findAllByOrderByNumeroAsc();
	
}	

