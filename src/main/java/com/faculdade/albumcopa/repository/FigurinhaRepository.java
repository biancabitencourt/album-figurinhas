package com.faculdade.albumcopa.repository;

import com.faculdade.albumcopa.model.Figurinha;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface FigurinhaRepository extends JpaRepository<Figurinha, Long> {
	
	List<Figurinha> findBySelecao(String selecao);
	List<Figurinha> findByRaraTrue();
}
